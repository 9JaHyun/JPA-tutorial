package com.book._07_advanced_mapping._03_composite_key._02_Recong._01_idclass;

import java.io.Serializable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class GrandChildId implements Serializable {

    private ChildId3 childId3;
    private String id;

}
