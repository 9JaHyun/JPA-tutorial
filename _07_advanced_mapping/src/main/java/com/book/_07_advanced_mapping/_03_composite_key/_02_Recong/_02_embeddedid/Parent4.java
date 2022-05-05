package com.book._07_advanced_mapping._03_composite_key._02_Recong._02_embeddedid;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Parent4 {

    @Id
    @Column(name = "PARENT_ID")
    private String id;

    private String name;
}
