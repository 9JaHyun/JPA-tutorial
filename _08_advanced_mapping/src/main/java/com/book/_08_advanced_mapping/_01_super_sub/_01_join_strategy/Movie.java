package com.book._08_advanced_mapping._01_super_sub._01_join_strategy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class Movie extends Item{

    private String director;
    private String actor;
}
