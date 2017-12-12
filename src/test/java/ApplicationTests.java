import com.leapest.project1.Application;
        import com.leapest.project1.api.dto.AddressDTO;
import com.leapest.project1.api.dto.SalesOrderItemDTO;
import com.leapest.project1.config.DatabaseConfigurationTest;
import com.leapest.project1.dal.entity.Address;
import com.leapest.project1.dal.entity.SalesOrderItem;
import com.leapest.project1.dal.entity.dv.AddressType;
import com.leapest.project1.service.mapper.AddressMapper;
import com.leapest.project1.service.mapper.SalesOrderItemMapper;
import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = {DatabaseConfigurationTest.class, Application.class})
public class ApplicationTests {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testAddressDTOMaping(){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setFirstName("Diego");
        addressDTO.setLastName("Antonelli");
        addressDTO.setStreet("AV A P Della, 1528 - Jd Nicolau");
        addressDTO.setState("Santa Catarina");
        addressDTO.setCountry("Brazil");
        addressDTO.setEmail("diego.antonelli@hotmail.com");
        addressDTO.setPhone("+5548996822033");
        addressDTO.setType("COMERCIAL");

        Address address = AddressMapper.makeAddress(addressDTO);

        logger.info("Address mapped object: {}", address);

        assertNotNull(address);
        assertNull(address.getId());
        assertEquals(addressDTO.getFirstName(),address.getFirstName());
        assertEquals(addressDTO.getLastName(),address.getLastName());
        assertEquals(addressDTO.getStreet(),address.getStreet());
        assertEquals(addressDTO.getState(),address.getState());
        assertEquals(addressDTO.getCountry(),address.getCountry());
        assertEquals(addressDTO.getEmail(),address.getEmail());
        assertEquals(addressDTO.getPhone(),address.getPhone());
        assertEquals(AddressType.COMERCIAL, address.getType());
    }

    @Test
    public void testSalesOrderItemDTOMapping(){
        SalesOrderItemDTO  salesOrderItemDTO = new SalesOrderItemDTO();
        salesOrderItemDTO.setItemDeliveryStatus("DELIVERED");
        salesOrderItemDTO.setQuantity("1000");
        salesOrderItemDTO.setAmount(new BigDecimal(736874.92));
        salesOrderItemDTO.setProductName("Product 1");

        SalesOrderItem salesOrderItem = SalesOrderItemMapper.makeSalesOrderItem(salesOrderItemDTO);

        logger.info("Sales Item mapped object: {}", salesOrderItem);

        assertNotNull(salesOrderItem);
        assertNull(salesOrderItem.getId());
        assertEquals(salesOrderItemDTO.getAmount(), salesOrderItem.getAmount());
        assertNull(salesOrderItem.getProduct().getId());
        assertEquals(salesOrderItemDTO.getProductName(), salesOrderItem.getProduct().getName());
        assertEquals(salesOrderItemDTO.getQuantity(), salesOrderItem.getQuantity().toString());
    }
}
