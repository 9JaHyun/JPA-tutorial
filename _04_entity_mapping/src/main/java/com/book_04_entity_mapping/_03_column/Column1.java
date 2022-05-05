package com.book_04_entity_mapping._03_column;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Column1 {

    @Id
    // 기본적인 이름을 변경할 수 있다.
    @Column(name = "ID")
    private long id;

    // 다양한 제약조건을 줄 수 있다.
    @Column(nullable = true, length = 10)
    private String name;

    // 메모리 상에서만 임시로 사용하고 싶을 때 쓴다 -> 영속성 컨텍스트에서만 사용
    @Transient
    private int tempNum;

    @Access(AccessType.FIELD)
    private String fieldAccess;

    // GETTER를 이용해 등록하는 방식은 PROPERTY
    @Access(AccessType.PROPERTY)
    public String code() {
        return fieldAccess + tempNum;
    }

}
