package com.book._07_advanced_mapping._03_composite_key._02_Recong._01_idclass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@IdClass(ChildId3.class)
public class Child3 {

    @Id
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    public Parent3 parent3;

    @Id
    @Column(name = "CHILD_ID")
    private String childId;

    private String name;
}
