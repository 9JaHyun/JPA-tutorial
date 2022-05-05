package com.book._07_advanced_mapping._04_join_table._01_join_column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 1. 조인 컬럼 사용하기
 *  - 외래 키 컬럼(이를 조인 컬럼이라고 함)을 이용하여 관리하는 방법이다.
 *  - 관계를 맺기 전까지는 둘 사이에 아무 관계가 없기 때문에 null을 입력해야 한다.
 *  - 이렇게 null을 허용하는 관계를 선택적 비식별 관계라 한다.
 *
 *  주의점
 *      - 선택적 비식별 관계는 null을 허용하기 때문에 OUTER JOIN 을 사용해야 한다.
 *      - 만약 INNER JOIN 시 관계가 맺어져 있지 않은 ROW는 조회가 안됨.
 *      - 또 null을 어떻게 처리를 해야 하는지에 대해 주의점을 가져야 함.
 */
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;
}
