package com.leapest.project1.dal.impl;

import com.leapest.project1.dal.entity.Product;
import com.leapest.project1.dal.repository.ProductRepository;
import com.leapest.project1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service to handle Product entities
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Find a product by id
     * @param id
     * @return Optional Product
     */
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findOne(id);
    }
}
