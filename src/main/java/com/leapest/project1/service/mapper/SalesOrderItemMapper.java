package com.leapest.project1.service.mapper;

import com.leapest.project1.api.dto.SalesOrderItemDTO;
import com.leapest.project1.api.dto.builder.SalesOrderItemDTOBuilder;
import com.leapest.project1.dal.entity.Product;
import com.leapest.project1.dal.entity.SalesOrderItem;
import com.leapest.project1.dal.entity.dv.DeliveryStatus;
import org.assertj.core.util.Strings;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to transform SalesOrderItemDTO in SalesOrderItem Entity and vice versa
 */
public class SalesOrderItemMapper {

    /**
     * Transform SalesOrderItemDTO object in SalesOrderItem object
     * @param salesOrderItemDTO
     * @return SalesOrderItem object
     */
    public static SalesOrderItem makeSalesOrderItem(SalesOrderItemDTO salesOrderItemDTO) {

        Integer quantity = !Strings.isNullOrEmpty(salesOrderItemDTO.getQuantity()) ? Integer.valueOf(salesOrderItemDTO.getQuantity()) : 0;
        Long id = !Strings.isNullOrEmpty(salesOrderItemDTO.getId()) ? Long.valueOf(salesOrderItemDTO.getId()) : null;
        DeliveryStatus deliveryStatus = !Strings.isNullOrEmpty(salesOrderItemDTO.getItemDeliveryStatus()) ? DeliveryStatus.valueOf(salesOrderItemDTO.getItemDeliveryStatus()) : DeliveryStatus.PROCESSING;

        Long productId = !Strings.isNullOrEmpty(salesOrderItemDTO.getProductId()) ? Long.valueOf(salesOrderItemDTO.getProductId()) : null;
        Product product = new Product(productId, salesOrderItemDTO.getProductName());

        return new SalesOrderItem(id, product, quantity, deliveryStatus, salesOrderItemDTO.getAmount(), null);
    }

    /**
     * Transform SalesOrderItem object in SalesOrderItemDTO object
     * @param salesOrderItem
     * @return SalesOrderItemDTO object
     */
    public static SalesOrderItemDTO makeSalesOrderItemDTO(SalesOrderItem salesOrderItem) {
        SalesOrderItemDTOBuilder builder = SalesOrderItemDTO.newBuilder()
                .withId(salesOrderItem.getId())
                .withProductId(salesOrderItem.getProduct().getId())
                .withProductName(salesOrderItem.getProduct().getName())
                .withAmount(salesOrderItem.getAmount())
                .withQuantity(salesOrderItem.getQuantity() != null ? salesOrderItem.getQuantity().toString() : null)
                .withItemDeliveryStatus(salesOrderItem.getItemDeliveryStatus());

        return builder.createSalesOrderItemDTO();
    }

    /**
     * Transform a list of SalesOrderItem in a list of SalesOrderItemDTO
     * @param salesOrderItems
     * @return List of SalesOrderItemDTO objects
     */
    public static List<SalesOrderItemDTO> makeSalesOrderItemDTOList(Collection<SalesOrderItem> salesOrderItems) {
        return salesOrderItems.stream()
                .map(SalesOrderItemMapper::makeSalesOrderItemDTO)
                .collect(Collectors.toList());
    }

    /**
     * Transform a lista of SalesOrderItemDTO in a list of SalesOrderItem
     * @param salesOrderItemsDTO
     * @return List of SalesOrderItem objects
     */
    public static List<SalesOrderItem> makeSalesOrderItemList(List<SalesOrderItemDTO> salesOrderItemsDTO) {
        return salesOrderItemsDTO.stream()
                .map(SalesOrderItemMapper::makeSalesOrderItem)
                .collect(Collectors.toList());
    }
}
