package com.leapest.project1.api.dto.builder;

import com.leapest.project1.api.dto.AddressDTO;
import com.leapest.project1.dal.entity.dv.AddressType;

/**
 * Class to build AddressDTO objects
 */
public class AddressDTOBuilder {
    private String id;
    private String type;
    private String firstName;
    private String lastName;
    private String street;
    private String state;
    private String country;
    private String email;
    private String phone;

    public AddressDTOBuilder withId(Long id) {
        if(id != null)
        this.id = id.toString();
        return this;
    }

    public AddressDTOBuilder withType(AddressType type) {
        this.type = type != null ? type.name() : null;
        return this;
    }

    public AddressDTOBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AddressDTOBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AddressDTOBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public AddressDTOBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public AddressDTOBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public AddressDTOBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public AddressDTOBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public AddressDTO createAddressDTO() {
        return new AddressDTO(id, type, firstName, lastName, street, state, country, email, phone);
    }
}
