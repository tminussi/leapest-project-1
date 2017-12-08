package com.leapest.project1.dal.repository;

import com.leapest.project1.dal.entity.SalesOrder;
import org.springframework.stereotype.Repository;

@Repository
public class SalesOrderRepository extends GenericRepository<SalesOrder>{

    public SalesOrderRepository() {
        super(SalesOrder.class);
    }
}
