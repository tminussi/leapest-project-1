package com.leapest.project1.dal.repository;

import com.leapest.project1.dal.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository extends GenericRepository<Product> {

    public ProductRepository() {
        super(Product.class);
    }
}
