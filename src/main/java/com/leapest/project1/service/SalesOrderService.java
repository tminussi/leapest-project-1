package com.leapest.project1.service;

import com.leapest.project1.api.dto.AddressDTO;
import com.leapest.project1.api.dto.SalesOrderDTO;
import com.leapest.project1.exception.EntityNotFoundException;
import com.leapest.project1.exception.InvalidIdException;

import java.util.List;
import java.util.Optional;

public interface SalesOrderService {
    SalesOrderDTO save(SalesOrderDTO salesOrder);
    void update(String id, AddressDTO deliveryAddress) throws EntityNotFoundException, InvalidIdException;
    void delete(String id) throws EntityNotFoundException, InvalidIdException;
    List<SalesOrderDTO> findAll();
    Optional<SalesOrderDTO> findById(String id) throws EntityNotFoundException, InvalidIdException;
}
