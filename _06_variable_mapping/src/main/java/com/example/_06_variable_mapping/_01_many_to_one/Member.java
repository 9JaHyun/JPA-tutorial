package com.example._06_variable_mapping._01_many_to_one;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // 양방향 연관관계를 맺기 위해서는 ManyToOne + OneToMany를 사용해야 한다.
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public void changeTeam(Team team) {
        this.team = team;

        // 무한루프 방지
        if (!team.getMembers().contains(this)) {
            team.getMembers().add(this);
        }
    }
}
