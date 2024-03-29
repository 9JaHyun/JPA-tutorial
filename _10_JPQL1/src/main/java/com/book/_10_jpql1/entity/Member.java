package com.book._10_jpql1.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public void setTeam(Team team) {
        this.team = team;

        if (!team.getMembers().contains(this)) {
            team.getMembers().add(this);
        }
    }
}
