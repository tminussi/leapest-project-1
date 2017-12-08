package com.leapest.project1.service;

import com.leapest.project1.api.dto.SalesOrderDTO;

import java.util.List;
import java.util.Optional;

public interface SalesOrderService {
    void save(SalesOrderDTO salesOrder);
    void update(SalesOrderDTO salesOrder);
    void delete(SalesOrderDTO salesOrder);
    List<SalesOrderDTO> findAll();
    Optional<SalesOrderDTO> findById(String id);
}
