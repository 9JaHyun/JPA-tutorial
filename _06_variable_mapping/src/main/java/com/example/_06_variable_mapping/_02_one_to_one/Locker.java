package com.example._06_variable_mapping._02_one_to_one;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Getter;

@Getter
@Entity
public class Locker {

    @Id
    @Column(name = "locker_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // 양방향 연관관계를 맺기 위해서는 해당 방법을 사용
    //
    @OneToOne(mappedBy = "locker")
    private Member member;
}
