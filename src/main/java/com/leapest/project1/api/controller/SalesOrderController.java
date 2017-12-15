package com.leapest.project1.api.controller;

import com.leapest.project1.api.dto.AddressDTO;
import com.leapest.project1.api.dto.ErrorDTO;
import com.leapest.project1.api.dto.SalesOrderDTO;
import com.leapest.project1.exception.EntityNotFoundException;
import com.leapest.project1.exception.InvalidIdException;
import com.leapest.project1.service.SalesOrderService;
import org.assertj.core.util.Strings;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
/**
 * Controller to manage requests
 */
public class SalesOrderController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SalesOrderService salesOrderService;

    /**
     * List all sales orders
     *
     * @return List of SalesOrderDTO or error
     */
    @GetMapping
    public Object listAll() {
        logger.info("Listing all SalesOrderDTO object from database");
        try {
            List<SalesOrderDTO> list = salesOrderService.findAll();
            if (!list.isEmpty())
                return list;
            return ResponseEntity.noContent().build();
        } catch (HibernateException e) {
            logger.error("An error occured during listing of SalesOrderDTO objects from database: Technical error: {}", e.getMessage());
            return ResponseEntity.badRequest().body(new ErrorDTO("Some error happened during detailing sale order: " + e.getLocalizedMessage()));
        }
    }

    /**
     * List one sales order by id
     *
     * @param id
     * @return SalesOrderDTO or error
     * @throws EntityNotFoundException
     * @throws InvalidIdException
     */
    @GetMapping(value = "/detail")
    public ResponseEntity listSalesOrder(@Valid @RequestParam(name = "id") String id) throws EntityNotFoundException, InvalidIdException {
        logger.info("Detailing SalesOrderDTO object from database with id {}", id);
        try {
            if (!Strings.isNullOrEmpty(id)) {
                Optional<SalesOrderDTO> salesOrder = salesOrderService.findById(id);
                if (salesOrder.isPresent())
                    return ResponseEntity.ok(salesOrder.get());
            }
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            logger.error("No SalesOrderDTO found with id {}", id);
            return ResponseEntity.noContent().build();
        } catch (HibernateException e) {
            logger.error("Error during detailing of SalesOrderDTO with id {}. Technical error: {}", id, e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Create a new sales order
     *
     * @param salesOrder
     * @return SalesOrderDTO or error
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createSalesOrder(@Valid @RequestBody SalesOrderDTO salesOrder) {
        logger.info("Creating new SalesOrder entity on database");
        try {
            SalesOrderDTO savedOrderDTO = salesOrderService.save(salesOrder);
            logger.info("SalesOrder created with id {}", savedOrderDTO.getId());
            return ResponseEntity.ok(savedOrderDTO);
        } catch (HibernateException e) {
            logger.error("Error creating SalesOrder on dabase. Technical error: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            String errors = e.getConstraintViolations().stream()
                    .map(ex -> ex.getPropertyPath().toString())
                    .collect(Collectors.joining(","));

            logger.error("Some fields are not filled during creating SalesOrder object. Fields: {}", errors);
            return ResponseEntity.badRequest().body(new ErrorDTO("Some fields need to be filled: " + errors));
        }
    }

    /**
     * Update delivery address of a Sales Order
     *
     * @param id
     * @param addressDTO
     * @return SalesOrderDTO or error
     * @throws InvalidIdException
     */
    @PutMapping(value = "/update/{id}")
    public ResponseEntity updateSalesOrder(@Valid @PathVariable("id") String id, @Valid @RequestBody AddressDTO addressDTO) throws InvalidIdException {
        logger.info("Updating an existing SalesOrder on database with id {}", id);
        try {
            SalesOrderDTO updatedOrderDTO = salesOrderService.update(id, addressDTO);
            logger.info("SalesOrder with id {} updated", id);
            return ResponseEntity.ok(updatedOrderDTO);
        } catch (HibernateException e) {
            logger.error("Error during updating a SalesOrder entity with id {}. Technical error: {}", id, e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException e) {
            logger.error("No SalesOrderDTO found with id {}", id);
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    /**
     * Delete a sales order by id
     *
     * @param id
     * @return HTTP code 200 or 400
     * @throws InvalidIdException
     */
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteSalesOrder(@Valid @PathVariable("id") String id) throws InvalidIdException {
        logger.info("Deleting an existing SalesOrder on database with id {}", id);
        try {
            salesOrderService.delete(id);
            logger.info("SalesOrder with id {} deleted", id);
            return ResponseEntity.ok().body(null);
        } catch (EntityNotFoundException e) {
            logger.error("No SalesOrderDTO found with id {}", id);
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        } catch (HibernateException e) {
            logger.error("Error during deleting a SalesOrder entity with id {}. Technical error: {}", id, e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
