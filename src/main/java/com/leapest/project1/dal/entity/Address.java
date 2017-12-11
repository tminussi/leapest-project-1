package com.leapest.project1.dal.entity;

import com.leapest.project1.dal.entity.dv.AddressType;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Audited
@Entity
@Table(name = "adresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Audited
    @Column(name = "address_id")
    private Long id;

    @Audited
    @NotNull
    @Column
    @Enumerated(EnumType.STRING)
    private AddressType type;

    @Audited
    @NotNull
    @Column
    private String firstName;

    @Audited
    @NotNull
    @Column
    private String lastName;

    @Audited
    @NotNull
    @Column
    private String street;

    @Audited
    @NotNull
    @Column
    private String state;

    @Audited
    @NotNull
    @Column
    private String country;

    @Audited
    @NotNull
    @Column
    private String email;

    @Audited
    @Column
    private String phone;

    public Address() {
    }

    public Address(Long id, AddressType type, String firstName, String lastName, String street, String state, String country, String email, String phone) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddressType getType() {
        return type;
    }

    public void setType(AddressType type) {
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

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", type=" + type +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
