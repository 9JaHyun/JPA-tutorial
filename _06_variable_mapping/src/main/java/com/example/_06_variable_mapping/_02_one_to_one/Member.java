package com.example._06_variable_mapping._02_one_to_one;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Getter;

// 일대일 관계에서의 핵심은 어디에 연관관계 주인을 설정할지? 이다.
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // 양방향 연관관계를 맺기 위해서는 ManyToOne + OneToMany를 사용해야 한다.
    @OneToOne
    @JoinColumn(name = "locker_id")
    private Locker locker;
}
