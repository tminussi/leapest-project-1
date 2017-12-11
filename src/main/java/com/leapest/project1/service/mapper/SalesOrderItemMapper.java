package com.leapest.project1.service.mapper;

import com.leapest.project1.api.dto.AddressDTO;
import com.leapest.project1.api.dto.SalesOrderItemDTO;
import com.leapest.project1.api.dto.builder.AddressDTOBuilder;
import com.leapest.project1.api.dto.builder.SalesOrderItemDTOBuilder;
import com.leapest.project1.dal.entity.Address;
import com.leapest.project1.dal.entity.Product;
import com.leapest.project1.dal.entity.SalesOrderItem;
import com.leapest.project1.dal.entity.dv.AddressType;
import com.leapest.project1.dal.entity.dv.DeliveryStatus;
import org.assertj.core.util.Strings;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SalesOrderItemMapper {

    public static SalesOrderItem makeSalesOrderItem(SalesOrderItemDTO salesOrderItemDTO) {

        Integer quantity = !Strings.isNullOrEmpty(salesOrderItemDTO.getQuantity()) ? Integer.valueOf(salesOrderItemDTO.getQuantity()) : 0;
        Long id = !Strings.isNullOrEmpty(salesOrderItemDTO.getId()) ? Long.valueOf(salesOrderItemDTO.getId()) : null;
        DeliveryStatus deliveryStatus = !Strings.isNullOrEmpty(salesOrderItemDTO.getItemDeliveryStatus()) ? DeliveryStatus.valueOf(salesOrderItemDTO.getItemDeliveryStatus()) : DeliveryStatus.PROCESSING;

        Long productId = !Strings.isNullOrEmpty(salesOrderItemDTO.getProductId())?Long.valueOf(salesOrderItemDTO.getProductId()):null;
        Product product = new Product(productId, salesOrderItemDTO.getProductName());

        return new SalesOrderItem(id, product, quantity, deliveryStatus, salesOrderItemDTO.getAmount(), null);
    }


    public static SalesOrderItemDTO makeSalesOrderItemDTO(SalesOrderItem salesOrderItem) {
        SalesOrderItemDTOBuilder builder = SalesOrderItemDTO.newBuilder()
                .withId(salesOrderItem.getId())
                .withProductId(salesOrderItem.getProduct().getId())
                .withProductName(salesOrderItem.getProduct().getName())
                .withAmount(salesOrderItem.getAmount())
                .withItemDeliveryStatus(salesOrderItem.getItemDeliveryStatus());

        return builder.createSalesOrderItemDTO();
    }


    public static List<SalesOrderItemDTO> makeSalesOrderItemDTOList(Collection<SalesOrderItem> salesOrderItems) {
        return salesOrderItems.stream()
                .map(SalesOrderItemMapper::makeSalesOrderItemDTO)
                .collect(Collectors.toList());
    }

    public static List<SalesOrderItem> makeSalesOrderItemList(List<SalesOrderItemDTO> salesOrderItemsDTO) {
        return salesOrderItemsDTO.stream()
                .map(SalesOrderItemMapper::makeSalesOrderItem)
                .collect(Collectors.toList());
    }
}
