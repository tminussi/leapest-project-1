package com.leapest.project1.dal.entity;

import com.leapest.project1.dal.entity.dv.OrderStatus;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Audited
@Entity
@Table(name = "sales_orders")
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Audited
    @Column(name="sales_order_id")
    private Long id;

    @Audited
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_address_id")
    private Address deliveryAddress;

    @Audited
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="invoice_address_id")
    private Address invoiceAddress;

    @Audited
    @OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<SalesOrderItem> salesOrderItems;

    @Audited
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", length = 20)
    private OrderStatus orderStatus;

    public SalesOrder() {
    }

    public SalesOrder(Address deliveryAddress, Address invoiceAddress, List<SalesOrderItem> salesOrderItems, OrderStatus orderStatus) {
        this.deliveryAddress = deliveryAddress;
        this.invoiceAddress = invoiceAddress;
        this.salesOrderItems = salesOrderItems;
        this.orderStatus = orderStatus;
        if(salesOrderItems != null && !salesOrderItems.isEmpty()){
            salesOrderItems.stream()
                    .filter(item -> item.getSalesOrder() == null)
                    .forEach(item -> item.setSalesOrder(this));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Address getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(Address invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public List<SalesOrderItem> getSalesOrderItems() {
        return salesOrderItems;
    }

    public void setSalesOrderItems(List<SalesOrderItem> salesOrderItems) {
        this.salesOrderItems = salesOrderItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
