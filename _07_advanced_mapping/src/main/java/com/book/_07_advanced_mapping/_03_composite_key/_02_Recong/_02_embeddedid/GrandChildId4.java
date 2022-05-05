package com.book._07_advanced_mapping._03_composite_key._02_Recong._02_embeddedid;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Embeddable
public class GrandChildId4 implements Serializable {

    private ChildId4 childId4;

    @Column(name = "GRANDCHILD_ID")
    private String id;
}
