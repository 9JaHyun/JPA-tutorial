package com.book._05_relation_mapping._02_twi_way;

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

    // 양쪽에 모두 set을 해주는 것이 훨씬 자연스러움
    // 그래서 양방향 편의 메서드라는 것을 만들어 한번에 연관관계를 설정하자.
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
