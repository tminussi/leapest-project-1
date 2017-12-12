package com.leapest.project1.dal.impl;

import com.leapest.project1.Application;
import com.leapest.project1.api.dto.AddressDTO;
import com.leapest.project1.api.dto.SalesOrderDTO;
import com.leapest.project1.api.dto.SalesOrderItemDTO;
import com.leapest.project1.config.DatabaseConfigurationTest;
import com.leapest.project1.dal.entity.SalesOrder;
import com.leapest.project1.exception.EntityNotFoundException;
import com.leapest.project1.exception.InvalidIdException;
import com.leapest.project1.service.SalesOrderService;
import com.sun.tools.javac.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = {DatabaseConfigurationTest.class, Application.class, SalesOrderService.class, SalesOrderServiceImpl.class, SalesOrder.class})
public class SalesOrderServiceImplTests {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SalesOrderService salesOrderService;

    @Test
    public void testSaving() throws EntityNotFoundException, InvalidIdException {
        SalesOrderDTO salesOrderDTO = createNewSalesOrder();
        Optional<SalesOrderDTO> findSale = salesOrderService.findById(salesOrderDTO.getId());
        assertEquals(salesOrderDTO.getId(), findSale.get().getId());
        assertEquals("Diego", findSale.get().getDeliveryAddress().getFirstName());
        assertEquals("Product 1", findSale.get().getSalesOrderItems().get(0).getProductName());
    }

    private SalesOrderDTO createNewSalesOrder() {
        SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
        salesOrderDTO.setOrderStatus("REGISTERED");
        AddressDTO deliveryAddress = new AddressDTO();
        deliveryAddress.setFirstName("Diego");
        deliveryAddress.setLastName("Antonelli");
        deliveryAddress.setStreet("AV A P Della, 1528 - Jd Nicolau");
        deliveryAddress.setState("Santa Catarina");
        deliveryAddress.setCountry("Brazil");
        deliveryAddress.setEmail("diego.antonelli@hotmail.com");
        deliveryAddress.setPhone("+5548996822033");
        deliveryAddress.setType("COMERCIAL");
        AddressDTO invoiceAddress = deliveryAddress;
        salesOrderDTO.setDeliveryAddress(deliveryAddress);
        salesOrderDTO.setInvoiceAddress(invoiceAddress);

        SalesOrderItemDTO item = new SalesOrderItemDTO();
        item.setProductName("Product 1");
        item.setAmount(new BigDecimal(2123.44));
        item.setItemDeliveryStatus("PROCESSING");
        item.setQuantity("123");

        salesOrderDTO.setSalesOrderItems(List.of(item));

        salesOrderDTO = salesOrderService.save(salesOrderDTO);
        return salesOrderDTO;
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteWithError() throws EntityNotFoundException, InvalidIdException {
        salesOrderService.delete("1234");
        assertNull(salesOrderService.findById("1234").orElse(null));
    }

    @Test(expected = InvalidIdException.class)
    public void testInvalidId() throws InvalidIdException, EntityNotFoundException {
        salesOrderService.delete("7786fdjs");
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeletingSalesOrder() throws InvalidIdException, EntityNotFoundException {
        SalesOrderDTO salesOrder = createNewSalesOrder();
        assertNotNull(salesOrder);
        salesOrderService.delete(salesOrder.getId());
        assertNull(salesOrderService.findById(salesOrder.getId()).orElse(null));
    }

    @Test
    public void testUpdatingDeliveryAddress() throws InvalidIdException, EntityNotFoundException {
        SalesOrderDTO salesOrder = createNewSalesOrder();
        assertNotNull(salesOrder);
        assertEquals("AV A P Della, 1528 - Jd Nicolau", salesOrder.getDeliveryAddress().getStreet());
        AddressDTO deliveryAddress = new AddressDTO();
        deliveryAddress.setFirstName("Diego");
        deliveryAddress.setLastName("Antonelli");
        deliveryAddress.setStreet("Test Avenue");
        deliveryAddress.setState("Santa Catarina");
        deliveryAddress.setCountry("Brazil");
        deliveryAddress.setEmail("diego.antonelli@hotmail.com");
        deliveryAddress.setPhone("+5548996822033");
        deliveryAddress.setType("PERSONAL");
        salesOrderService.update(salesOrder.getId(), deliveryAddress);
        Optional<SalesOrderDTO> updatedEntity = salesOrderService.findById(salesOrder.getId());
        assertNotNull(updatedEntity.orElse(null));
        assertEquals(deliveryAddress.getStreet(), updatedEntity.get().getDeliveryAddress().getStreet());
    }
}
