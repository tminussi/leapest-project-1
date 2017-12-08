package com.leapest.project1.api.controller;

import com.leapest.project1.api.dto.SalesOrderDTO;
import com.leapest.project1.dal.entity.SalesOrder;
import com.leapest.project1.service.SalesOrderService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/sales")
@RequestScope
public class SalesOrderController {
    @Autowired
    private SalesOrderService salesOrderService;

    @GetMapping
    public SalesOrderDTO listSalesOrder(String id) {
        Optional<SalesOrderDTO> salesOrder = salesOrderService.findById(id);
        if(salesOrder.isPresent())
            return salesOrder.get();
        else
            return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSalesOrder(@Valid @RequestBody SalesOrderDTO salesOrder) throws HibernateException {
        salesOrderService.save(salesOrder);
    }
}
