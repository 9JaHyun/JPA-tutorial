package com.book._07_advanced_mapping._01_super_sub._03_table_per_class;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class Movie extends Item {

    private String director;
    private String actor;
}
