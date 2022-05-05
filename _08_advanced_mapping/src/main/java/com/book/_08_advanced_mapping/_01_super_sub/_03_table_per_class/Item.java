package com.book._08_advanced_mapping._01_super_sub._03_table_per_class;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * 3. 구현 클래스마다 테이블링
 *  - 하나의 부모 테이블을 만들지 않고, 공통되는 속성들을 각각의 자식 테이블에게 뿌리는 방식.
 *  - 잘 사용하지는 않는 방법
 *  - 구분 컬럼 (@DiscriminatorColumn)을 따로 사용하지 않음
 *  장점
 *      - 서브 타입을 구분해서 처리할 때 효과적
 *      - not null 제약 조건을 사용할 수 있다.
 *
 *  단점
 *      - 여러 자식 테이블을 함께 조회할 때 성능이 느림. (UNION 사용 해야 함)
 *      - 자식 테이블을 통합해 쿼리하기가 어려움. (Album, Movie 모두 Item이지만, Item 자체를 조회 불가)
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;
    private String name;
    private int price;
}
