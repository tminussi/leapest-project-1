package com.leapest.project1.dal.impl;

import com.leapest.project1.dal.entity.Product;
import com.leapest.project1.dal.repository.ProductRepository;
import com.leapest.project1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findOne(id);
    }
}
