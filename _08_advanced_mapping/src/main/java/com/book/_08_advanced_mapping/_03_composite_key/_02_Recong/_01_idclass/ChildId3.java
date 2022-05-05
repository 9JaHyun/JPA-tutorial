package com.book._08_advanced_mapping._03_composite_key._02_Recong._01_idclass;

import java.io.Serializable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ChildId3 implements Serializable {

    private String parent;
    private String childId;

    public ChildId3() {
    }

    public ChildId3(String parent, String childId) {
        this.parent = parent;
        this.childId = childId;
    }
}
