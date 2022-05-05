package com.book._07_advanced_mapping._04_join_table._02_join_table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

/**
 * 2. 조인 테이블 사용하기
 *  - 조인 테이블이라는 별도의 테이블을 사용해서 연관관계를 관리 - ManyToMany 에서 ManyToOne 두개로 나누었던 것을 기억하자.
 */
@Entity
public class Member2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    @OneToOne
    @JoinTable(name = "MEMBER_LOCKER",
          joinColumns = @JoinColumn(name = "MEMBER_ID"),            // 현재 엔티티를 참조하는 외래 키
          inverseJoinColumns = @JoinColumn(name = "LOCKER_ID"))     // 반대 방향 엔티티를 참조하는 외래 키
    private Locker2 locker2;
}
