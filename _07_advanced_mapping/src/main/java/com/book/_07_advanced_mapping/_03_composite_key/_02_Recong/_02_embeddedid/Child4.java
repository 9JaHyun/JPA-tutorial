package com.book._07_advanced_mapping._03_composite_key._02_Recong._02_embeddedid;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Child4 {

    @EmbeddedId
    private ChildId4 id4;

    // @Id 와 다르게 외래키와 매핑한 연관관계를 기본키에도 매핑하려면 @MapsId 사용
    // @EmbeddedId 를 사용한 식별자 클래스의 기본 키 필드를 지정.
    @MapsId("parentId")
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    public Parent4 parent4;

    private String name;
}
