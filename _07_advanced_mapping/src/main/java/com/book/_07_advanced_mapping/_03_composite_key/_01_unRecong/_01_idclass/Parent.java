package com.book._07_advanced_mapping._03_composite_key._01_unRecong._01_idclass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 복합키 지원 1. @IdClass 를 통한 비식별 관계 매핑
 *
 *      - 식별자를 둘 이상 사용하려면 별도의 식별자 클래스를 만들어야 한다. (ParentId.class)
 *      - DB에 초점을 맞춘 방법.
 *
 *  복합키를 가지는 클래스
 *      - 추가적으로 식별자가 둘 이상이라면 동등성 비교(equals(), hashcode())를 재정의하자
 *      - 
 */
@EqualsAndHashCode
@Setter @Getter
@IdClass(ParentId.class)
@Entity
public class Parent {

    @Id
    @Column(name = "PARENT_ID1")
    private String id1;

    @Id
    @Column(name = "PARENT_ID2")
    private String id2;

    private String name;

}
