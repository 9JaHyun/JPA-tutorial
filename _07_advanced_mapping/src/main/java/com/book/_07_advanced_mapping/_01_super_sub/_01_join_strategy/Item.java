package com.book._07_advanced_mapping._01_super_sub._01_join_strategy;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.Getter;

/**
 * 1. 조인 전략
 *  - 서브 타입들의 엔티티들을 모두 테이블로 만들어 자식 테이블이 부모 테이블의 기본키를 받아 사용
 *  - 이때 부모 테이블의 기본키는 자식 테이블의 기본키 or 외래키로 사용.
 *
 *  주의점
 *      - DB 에서는 타입의 개념이 없기 때문에 타입을 구분하는 컬럼을 추가해야 함.
 *
 *  장점
 *      - 확실한 정규화
 *      - 외래 키 참조 무결성 제약조건을 활용할 수 있다.
 *      - 효율적인 저장공간 사용 가능
 *  단점
 *      - 많은 조인으로 인한 성능 저하
 *      - 조회 쿼리의 복잡함
 *      - 한 데이터를 등록할 때 두 번 Insert SQL이 날아감.
 */
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")    // 부모 클래스에 구분 컬럼 지정 (자식 테이블 구분용)
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
}
