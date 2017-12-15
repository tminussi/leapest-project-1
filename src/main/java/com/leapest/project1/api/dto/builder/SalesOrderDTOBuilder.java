package com.leapest.project1.api.dto.builder;

import com.leapest.project1.api.dto.AddressDTO;
import com.leapest.project1.api.dto.SalesOrderDTO;
import com.leapest.project1.api.dto.SalesOrderItemDTO;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Class to build Sales Order DTO objects
 */
public class SalesOrderDTOBuilder {
    private String id;
    private AddressDTO deliveryAddress;
    private AddressDTO invoiceAddress;
    private List<SalesOrderItemDTO> salesOrderItems;
    private String orderStatus;

    public SalesOrderDTOBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public SalesOrderDTOBuilder withDeliveryAddress(AddressDTO deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return this;
    }

    public SalesOrderDTOBuilder withInvoiceAddress(AddressDTO invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
        return this;
    }

    public SalesOrderDTOBuilder withSalesOrderItems(List<SalesOrderItemDTO> salesOrderItems) {
        this.salesOrderItems = salesOrderItems;
        return this;
    }

    public SalesOrderDTOBuilder withOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public SalesOrderDTO createSalesOrderDTO(){
        return new SalesOrderDTO(id, deliveryAddress, invoiceAddress, salesOrderItems, orderStatus);
    }
}
