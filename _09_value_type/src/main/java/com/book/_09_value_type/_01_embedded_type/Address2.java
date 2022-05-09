package com.book._09_value_type._01_embedded_type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public final class Address2 {

    @Column( name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "zipcode")
    private String zipCode;

    protected Address2() {
    }

    public Address2(String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }
}
