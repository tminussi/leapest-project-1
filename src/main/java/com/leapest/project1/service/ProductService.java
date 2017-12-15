package com.leapest.project1.service;

import com.leapest.project1.dal.entity.Product;

import java.util.Optional;

/**
 * Interface to define ProductService methods
 */
public interface ProductService {
    Optional<Product> findById(Long id);
}
