package com.leapest.project1.service.mapper;

import com.leapest.project1.api.dto.AddressDTO;
import com.leapest.project1.api.dto.SalesOrderDTO;
import com.leapest.project1.api.dto.SalesOrderItemDTO;
import com.leapest.project1.api.dto.builder.SalesOrderDTOBuilder;
import com.leapest.project1.dal.entity.Address;
import com.leapest.project1.dal.entity.SalesOrder;
import com.leapest.project1.dal.entity.SalesOrderItem;
import com.leapest.project1.dal.entity.dv.OrderStatus;
import org.assertj.core.util.Strings;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to transform SalesOrderDTO in SalesOrder Entity and vice versa
 */
public class SalesOrderMapper {

    /**
     * Transform SalesOrderDTO object in SalesOrder object
     * @param salesOrderDTO
     * @return SalesOrder object
     */
    public static SalesOrder makeSalesOrder(SalesOrderDTO salesOrderDTO) {
        Address deliveryAddress = AddressMapper.makeAddress(salesOrderDTO.getDeliveryAddress());
        Address invoiceAddress = AddressMapper.makeAddress(salesOrderDTO.getInvoiceAddress());
        List<SalesOrderItem> items = SalesOrderItemMapper.makeSalesOrderItemList(salesOrderDTO.getSalesOrderItems());
        OrderStatus orderStatus = !Strings.isNullOrEmpty(salesOrderDTO.getOrderStatus()) ? OrderStatus.valueOf(salesOrderDTO.getOrderStatus()) : OrderStatus.REGISTERED;

        return new SalesOrder(deliveryAddress, invoiceAddress, items, orderStatus);
    }

    /**
     * Transform SalesOrder object in SalesOrderDTO object
     * @param salesOrder
     * @return SalesOrderDTO object
     */
    public static SalesOrderDTO makeSalesOrderDTO(SalesOrder salesOrder) {
        String id = salesOrder.getId() != null ? salesOrder.getId().toString() : null;
        AddressDTO deliveryAddress = AddressMapper.makeAddressDTO(salesOrder.getDeliveryAddress());
        AddressDTO invoiceAddress = AddressMapper.makeAddressDTO(salesOrder.getInvoiceAddress());
        List<SalesOrderItemDTO> items = SalesOrderItemMapper.makeSalesOrderItemDTOList(salesOrder.getSalesOrderItems());

        SalesOrderDTOBuilder salesOrderDTOBuilder = SalesOrderDTO.newBuilder()
                .withId(id)
                .withDeliveryAddress(deliveryAddress)
                .withInvoiceAddress(invoiceAddress)
                .withSalesOrderItems(items)
                .withOrderStatus(salesOrder.getOrderStatus() != null ? salesOrder.getOrderStatus().name() : null);

        return salesOrderDTOBuilder.createSalesOrderDTO();
    }

    /**
     * Transform a list of SalesOrder in a list of SalesOrderDTO
     * @param salesOrders
     * @return List of SalesOrderDTO objects
     */
    public static List<SalesOrderDTO> makeSalesOrderDTOList(Collection<SalesOrder> salesOrders) {
        return salesOrders.stream()
                .map(SalesOrderMapper::makeSalesOrderDTO)
                .collect(Collectors.toList());
    }
}
