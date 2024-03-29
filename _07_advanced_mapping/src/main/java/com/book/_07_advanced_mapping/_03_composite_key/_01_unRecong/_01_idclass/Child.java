package com.book._07_advanced_mapping._03_composite_key._01_unRecong._01_idclass;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
public class Child {

    @Id
    private String id;

    @ManyToOne
    // 부모의 기본키가 복합키! -> @JoinColumns 사용해 각각 매핑
    @JoinColumns({
          @JoinColumn(name = "PARENT_ID1", referencedColumnName = "PARENT_ID1"),
          @JoinColumn(name = "PARENT_ID2", referencedColumnName = "PARENT_ID2")
    })
    private Parent parent;
}
