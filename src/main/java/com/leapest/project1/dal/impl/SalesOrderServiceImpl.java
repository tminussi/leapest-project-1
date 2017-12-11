package com.leapest.project1.dal.impl;

import com.leapest.project1.api.dto.SalesOrderDTO;
import com.leapest.project1.dal.entity.Product;
import com.leapest.project1.dal.entity.SalesOrder;
import com.leapest.project1.dal.repository.SalesOrderRepository;
import com.leapest.project1.service.ProductService;
import com.leapest.project1.service.SalesOrderService;
import com.leapest.project1.service.mapper.SalesOrderMapper;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalesOrderServiceImpl implements SalesOrderService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private ProductService productService;

    @Override
    public void save(SalesOrderDTO salesOrderDTO) {
        SalesOrder salesOrder = SalesOrderMapper.makeSalesOrder(salesOrderDTO);
        salesOrder.getSalesOrderItems().stream()
                .filter(item -> item.getProduct().getId() != null)
                .forEach(item -> {
                    Optional<Product> product = productService.findById(item.getProduct().getId());
                    if (product.isPresent())
                        item.setProduct(product.get());
                    else
                        item.getProduct().setId(null);
                });
        try {
            salesOrderRepository.save(salesOrder);
        } catch (HibernateException e) {
            logger.error("Error saving Sales Order: {}", e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void update(SalesOrderDTO salesOrderDTO) {

    }

    @Override
    public void delete(SalesOrderDTO salesOrderDTO) {

    }

    @Override
    public List<SalesOrderDTO> findAll() {
        List<SalesOrder> list = salesOrderRepository.listAll("id");
        if(!list.isEmpty())
            return SalesOrderMapper.makeSalesOrderDTOList(list);
        return new ArrayList<>();
    }

    @Override
    public Optional<SalesOrderDTO> findById(String id) {
        Optional<SalesOrder> salesOrder = salesOrderRepository.findOne(Long.valueOf(id));
        if (salesOrder.isPresent())
            return Optional.of(SalesOrderMapper.makeSalesOrderDTO(salesOrder.get()));
        else
            return Optional.empty();

    }
}
