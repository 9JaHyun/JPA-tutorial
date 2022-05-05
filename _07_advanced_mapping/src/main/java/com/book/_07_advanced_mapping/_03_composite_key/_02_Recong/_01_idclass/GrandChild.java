package com.book._07_advanced_mapping._03_composite_key._02_Recong._01_idclass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
@IdClass(GrandChildId.class)
public class GrandChild {

    @Id
    @ManyToOne
    @JoinColumns({
          @JoinColumn(name = "PARENT_ID"),
          @JoinColumn(name = "CHILD_ID")
    })
    private Child3 child3;

    @Id
    @Column(name = "GRANDCHILD_ID")
    private String id;

    private String name;
}
