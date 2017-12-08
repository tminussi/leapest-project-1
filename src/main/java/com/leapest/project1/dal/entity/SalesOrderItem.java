package com.leapest.project1.dal.entity;

import java.math.BigDecimal;

public class SalesOrderItem {
    private Long id;
    private Product product;
    private Integer quantity;
    private DeliveryStatus itemDeliveryStatus;
    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public DeliveryStatus getItemDeliveryStatus() {
        return itemDeliveryStatus;
    }

    public void setItemDeliveryStatus(DeliveryStatus itemDeliveryStatus) {
        this.itemDeliveryStatus = itemDeliveryStatus;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
