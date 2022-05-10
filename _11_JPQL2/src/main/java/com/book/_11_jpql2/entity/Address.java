package com.book._11_jpql2.entity;

import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipCode;

    public Address() {
    }

    public Address(String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }
}
