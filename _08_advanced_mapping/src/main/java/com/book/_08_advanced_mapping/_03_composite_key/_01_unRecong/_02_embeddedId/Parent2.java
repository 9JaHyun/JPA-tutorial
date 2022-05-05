package com.book._08_advanced_mapping._03_composite_key._01_unRecong._02_embeddedId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 복합키 지원 2. @EmbeddedId 를 통한 비식별 관계 매핑
 *  - 조금 더 객체지향적인 방법
 *
 *  선행조건
 *      - 복합키 클래스가 따로 있어야 한다.
 *      - 해당 복합키 클래스는 다음을 만족해야 한다.
 *          - @Embeddable 선언해야 함.
 *          - Serializable 인터페이스를 구현해야 함
 *          - equals, hashCode 를 구현해야 함
 *
 * @IdClass vs @EmbeddedId
 *  - 둘은 취향차이
 *  - @EmbeddedId 가 객체지향적이고, 중복이 없어 좋을 수 있으나, 특정 상황에서 JPQL이 더 길어질 수 있음.
 *      - @EmbeddedId : "select p.id.id1, p.id.id2 from Parent p"
 *      - @IdClass    : "select p.id1, p.id2 from Parent p"
 *  - 추가로 둘 다 @GenerateValue를 사용하지 못한다.
 *  - 식별 관계의 경우에는 모두 @Id를 붙여주면 된다.
 */
@EqualsAndHashCode
@Setter @Getter
@Entity
public class Parent2 {

    @EmbeddedId
    private ParentId2 parentId;

    private String name;
}
