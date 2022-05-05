package com.book_04_entity_mapping._01_Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

// JPA과 관리하는 엔티티임을 알리는 어노테이션
@Entity(name = "Entity1")   // 다른 엔티티와 이름이 겹치는 경우에만 name 속성을 사용
public class Entity1 {

    // Proxy 기능을 사용하기 때문에 Final 필드는 사용 불가

    // 고유한 식별자를 가져야 함. (보통 PK값)
    @Id
    private long id;
    private String name;

    public Entity1(String name) {
        this.name = name;
    }

    // Entity 적용 시 기본생성자는 필수 (Java Reflection)
    public Entity1(){}

}
