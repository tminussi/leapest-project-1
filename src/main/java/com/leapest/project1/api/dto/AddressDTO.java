package com.leapest.project1.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.leapest.project1.api.dto.builder.AddressDTOBuilder;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO{
    @JsonIgnore
    private String id;
    @NotNull(message = "Type can not be null!")
    private String type;
    @NotNull(message = "Firstname can not be null!")
    private String firstName;
    @NotNull(message = "Lastname can not be null!")
    private String lastName;
    @NotNull(message = "Street can not be null!")
    private String street;
    @NotNull(message = "State can not be null!")
    private String state;
    @NotNull(message = "Country can not be null!")
    private String country;
    @NotNull(message = "Email can not be null!")
    private String email;
    private String phone;

    public AddressDTO() {
    }

    public AddressDTO(String id, String type, String firstName, String lastName, String street, String state, String country, String email, String phone) {
        this.id = id;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.state = state;
        this.country = country;
        this.email = email;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static AddressDTOBuilder newBuilder()
    {
        return new AddressDTOBuilder();
    }
}
