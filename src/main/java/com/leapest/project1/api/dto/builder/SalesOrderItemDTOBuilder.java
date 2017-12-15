package com.leapest.project1.api.dto.builder;

import com.leapest.project1.api.dto.SalesOrderItemDTO;
import com.leapest.project1.dal.entity.dv.DeliveryStatus;

import java.math.BigDecimal;

/**
 * Class to build SalesOrderItemDTO objects
 */
public class SalesOrderItemDTOBuilder {
    private String id;
    private String productName;
    private String productId;
    private String quantity;
    private String itemDeliveryStatus;
    private BigDecimal amount;

    public SalesOrderItemDTOBuilder withId(Long id) {
        if (id != null)
            this.id = id.toString();
        return this;
    }

    public SalesOrderItemDTOBuilder withProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public SalesOrderItemDTOBuilder withProductId(Long productId) {
        if (productId != null)
            this.productId = productId.toString();
        return this;
    }

    public SalesOrderItemDTOBuilder withQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public SalesOrderItemDTOBuilder withItemDeliveryStatus(DeliveryStatus itemDeliveryStatus) {
        if (itemDeliveryStatus != null)
            this.itemDeliveryStatus = itemDeliveryStatus.name();
        return this;
    }

    public SalesOrderItemDTOBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public SalesOrderItemDTO createSalesOrderItemDTO() {
        return new SalesOrderItemDTO(id, productName, productId, quantity, itemDeliveryStatus, amount);
    }
}
