package com.leapest.project1.api.dto;

import java.io.Serializable;
import java.util.List;

public class SalesOrderDTO implements Serializable{
    private String id;
    private AddressDTO deliveryAddress;
    private AddressDTO invoiceAddress;
    private List<SalesOrderItemDTO> salesOrderItems;
    private String orderStatus;

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
}
