package com.book._09_value_type._01_embedded_type;

import javax.persistence.Embeddable;
import lombok.Getter;

// 기본 생성자가 필수로 있어야 한다.
// 값 객체는 불변타입으로 만드는게 정신건강에 좋다.
@Getter
@Embeddable
public final class Address {

    private String city;
    private String street;
    private String zipCode;

    protected Address() {
    }

    public Address(String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }
}
