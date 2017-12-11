package com.leapest.project1.api.controller;

import com.leapest.project1.api.dto.ErrorDTO;
import com.leapest.project1.api.dto.SalesOrderDTO;
import com.leapest.project1.service.SalesOrderService;
import org.assertj.core.util.Strings;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/sales")
@RequestScope
public class SalesOrderController {
    @Autowired
    private SalesOrderService salesOrderService;

    @GetMapping
    public Object listAll(){
        try {
            List<SalesOrderDTO> list = salesOrderService.findAll();
            if(!list.isEmpty())
                return list;
            return ResponseEntity.noContent().build();
        }catch(HibernateException e){
            return ResponseEntity.badRequest().body(new ErrorDTO("Some error happened during detailing sale order: "+e.getLocalizedMessage()));
        }
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ResponseEntity listSalesOrder(@RequestParam(name = "id") String id) {
        try {
            if (!Strings.isNullOrEmpty(id)) {
                Optional<SalesOrderDTO> salesOrder = salesOrderService.findById(id);
                if (salesOrder.isPresent())
                    return ResponseEntity.ok(salesOrder.get());
            }
            return ResponseEntity.noContent().build();
        }catch(HibernateException e){
            return ResponseEntity.badRequest().body(new ErrorDTO("Some error happened during detailing sale order: "+e.getLocalizedMessage()));
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createSalesOrder(@Valid @RequestBody SalesOrderDTO salesOrder){
        try {
            salesOrderService.save(salesOrder);
            return ResponseEntity.ok(salesOrder);
        }catch(HibernateException e){

        } catch (ConstraintViolationException e){
            e.printStackTrace();
            String errors = e.getConstraintViolations().stream()
                    .map(ex -> ex.getPropertyPath().toString())
                    .collect(Collectors.joining(","));

            return ResponseEntity.badRequest().body(new ErrorDTO("Some fields need to be filled: "+errors));
        }
        return null;
    }
}
