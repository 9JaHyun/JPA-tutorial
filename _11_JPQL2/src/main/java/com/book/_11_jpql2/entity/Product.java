package com.book._11_jpql2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int price;
    private int stockAmount;
}
