package com.leapest.project1.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class SalesOrderItemDTO implements Serializable{
    private String id;
    private String productName;
    private String productId;
    private String quantity;
    private String itemDeliveryStatus;
    private BigDecimal amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getItemDeliveryStatus() {
        return itemDeliveryStatus;
    }

    public void setItemDeliveryStatus(String itemDeliveryStatus) {
        this.itemDeliveryStatus = itemDeliveryStatus;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
