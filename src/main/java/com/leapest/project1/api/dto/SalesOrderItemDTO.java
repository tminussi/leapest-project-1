package com.leapest.project1.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.leapest.project1.api.dto.builder.AddressDTOBuilder;
import com.leapest.project1.api.dto.builder.SalesOrderItemDTOBuilder;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Class do handle items of a Sales Order DTO Objects
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalesOrderItemDTO{
    private String id;
    @NotNull(message = "Product name can not be null!")
    private String productName;
    private String productId;
    @NotNull(message = "Quantity can not be null!")
    private String quantity;
    private String itemDeliveryStatus;
    @NotNull(message = "Amount can not be null!")
    private BigDecimal amount;

    public SalesOrderItemDTO() {
    }

    public SalesOrderItemDTO(String id, String productName, String productId, String quantity, String itemDeliveryStatus, BigDecimal amount) {
        this.id = id;
        this.productName = productName;
        this.productId = productId;
        this.quantity = quantity;
        this.itemDeliveryStatus = itemDeliveryStatus;
        this.amount = amount;
    }

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

    public static SalesOrderItemDTOBuilder newBuilder() {
        return new SalesOrderItemDTOBuilder();
    }
}
