package com.book._07_advanced_mapping._01_super_sub._02_single_table;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * 2. 단일 테이블 전략
 *  - 자식 클래스에 대한 필드 값들을 모두 하나의 테이블에서 가지는 전략
 *  - 조인을 사용하지 않기 때문에 가장 빠른 방식.
 *
 *  주의점
 *      - nullable 을 허용해야 하기 때문에, 주의가 필요하다.
 *      - 구분 컬럼(@DiscriminatorColumn)을 반드시 설정해야 한다.
 *      - 자식 클래스에 @DiscriminatorValue 을 지정하지 않으면, 엔티티 이름을 기본적으로 사용함.
 *
 *  장점
 *      - 조인이 필요가 없어 조회 성능이 빠르다.
 *      - 단순한 쿼리
 *
 *  단점
 *      - 자식 엔티티가 매핑한 컬럼은 모두 null을 허용 해야 한다.
 *      - 단일 테이블이 너무 비대해질 수 있다. (일정 수준을 벗어나면 오히려 속도가 더 떨어짐.)
 *          - 이때가 바로 정규화가 필요한 시점.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;
    private String name;
    private int price;
}
