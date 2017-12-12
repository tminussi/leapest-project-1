package com.leapest.project1.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.leapest.project1.api.dto.builder.SalesOrderDTOBuilder;

import javax.validation.constraints.NotNull;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalesOrderDTO{
    private String id;
    @NotNull(message = "Delivery address can not be null!")
    private AddressDTO deliveryAddress;
    @NotNull(message = "Invoice address can not be null!")
    private AddressDTO invoiceAddress;
    @NotNull(message = "Items can not be null!")
    private List<SalesOrderItemDTO> salesOrderItems;
    private String orderStatus;

    public SalesOrderDTO() {
    }

    public SalesOrderDTO(String id, AddressDTO deliveryAddress, AddressDTO invoiceAddress, List<SalesOrderItemDTO> salesOrderItems, String orderStatus) {
        this.id = id;
        this.deliveryAddress = deliveryAddress;
        this.invoiceAddress = invoiceAddress;
        this.salesOrderItems = salesOrderItems;
        this.orderStatus = orderStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AddressDTO getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(AddressDTO deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public AddressDTO getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(AddressDTO invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public List<SalesOrderItemDTO> getSalesOrderItems() {
        return salesOrderItems;
    }

    public void setSalesOrderItems(List<SalesOrderItemDTO> salesOrderItems) {
        this.salesOrderItems = salesOrderItems;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public static SalesOrderDTOBuilder newBuilder(){
        return new SalesOrderDTOBuilder();
    }
}
