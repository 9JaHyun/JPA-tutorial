package com.book._07_advanced_mapping._04_join_table._02_join_table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Locker2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCKER_ID")
    private Long id;

    private String name;

    // 만약 양방향 매핑을 원하면 다음을 사용하자
    @OneToOne(mappedBy = "locker2")
    private Member2 member2;
}
