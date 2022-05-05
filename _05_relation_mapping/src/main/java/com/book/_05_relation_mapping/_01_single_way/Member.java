package com.book._05_relation_mapping._01_single_way;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // 다대일 연관관계
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Member() {
    }

    public Member(String name) {
        this.name = name;
    }
}
