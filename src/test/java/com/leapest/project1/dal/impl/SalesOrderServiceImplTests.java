package com.leapest.project1.dal.impl;

import com.leapest.project1.Application;
import com.leapest.project1.api.dto.AddressDTO;
import com.leapest.project1.api.dto.SalesOrderDTO;
import com.leapest.project1.api.dto.SalesOrderItemDTO;
import com.leapest.project1.config.DatabaseConfigurationTest;
import com.leapest.project1.config.EntityInterceptorTest;
import com.leapest.project1.dal.entity.SalesOrder;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = {DatabaseConfigurationTest.class, EntityInterceptorTest.class, Application.class, SalesOrderService.class, SalesOrderServiceImpl.class, SalesOrder.class})
public class SalesOrderServiceImplTests {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SalesOrderService salesOrderService;

    @Test
    public void testSaving(){
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

        salesOrderService.save(salesOrderDTO);
        Optional<SalesOrderDTO> findSale = salesOrderService.findById("1");
        assertEquals("1", findSale.get().getId());
        assertEquals("1", findSale.get().getSalesOrderItems().get(0).getProductId());
        assertEquals(item.getProductName(), findSale.get().getSalesOrderItems().get(0).getProductName());
    }
}
