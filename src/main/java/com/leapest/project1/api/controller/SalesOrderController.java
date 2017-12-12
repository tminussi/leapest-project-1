package com.leapest.project1.api.controller;

import com.leapest.project1.api.dto.AddressDTO;
import com.leapest.project1.api.dto.ErrorDTO;
import com.leapest.project1.api.dto.SalesOrderDTO;
import com.leapest.project1.exception.EntityNotFoundException;
import com.leapest.project1.exception.InvalidIdException;
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

    @GetMapping(value = "/detail")
    public ResponseEntity listSalesOrder(@Valid @RequestParam(name = "id") String id) throws EntityNotFoundException, InvalidIdException {
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
            SalesOrderDTO savedOrderDTO = salesOrderService.save(salesOrder);
            return ResponseEntity.ok(savedOrderDTO);
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

    @PutMapping(value = "/update/{id}")
    public ResponseEntity updateSalesOrder(@Valid @PathVariable("id") String id, @Valid @RequestBody AddressDTO addressDTO) throws InvalidIdException {
        try{
            salesOrderService.update(id, addressDTO);
            Optional<SalesOrderDTO> salesOrderDTO = salesOrderService.findById(id);
            return ResponseEntity.ok().body(salesOrderDTO.get());
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity updateSalesOrder(@Valid @PathVariable("id") String id) throws InvalidIdException {
        try{
            salesOrderService.delete(id);
            return ResponseEntity.ok().body(null);
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }
}
