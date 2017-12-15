package com.leapest.project1.dal.entity;

import com.leapest.project1.dal.entity.dv.DeliveryStatus;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Audited
@Entity
@Table(name = "sales_orders_items")
public class SalesOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Audited
    @Column(name = "item_id")
    private Long id;

    @Audited
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @Audited
    @Column
    @Min(value = 1)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Audited
    @Column(name = "delivery_status", length = 20)
    private DeliveryStatus itemDeliveryStatus;

    @Audited
    @Column
    @NotNull
    private BigDecimal amount;

    @Audited
    @ManyToOne
    @JoinColumn(name = "sales_order_id")
    private SalesOrder salesOrder;

    public SalesOrderItem() {
    }

    public SalesOrderItem(Long id, Product product, Integer quantity, DeliveryStatus itemDeliveryStatus, BigDecimal amount, SalesOrder salesOrder) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.itemDeliveryStatus = itemDeliveryStatus;
        this.amount = amount;
        this.salesOrder = salesOrder;
    }

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

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    @Override
    public String toString() {
        return "SalesOrderItem{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", itemDeliveryStatus=" + itemDeliveryStatus +
                ", amount=" + amount +
                ", salesOrder=" + salesOrder +
                '}';
    }
}
