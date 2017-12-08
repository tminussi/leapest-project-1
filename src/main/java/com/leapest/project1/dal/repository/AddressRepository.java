package com.leapest.project1.dal.repository;

import com.leapest.project1.dal.entity.Address;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepository extends GenericRepository<Address> {

    public AddressRepository() {
        super(Address.class);
    }
}
