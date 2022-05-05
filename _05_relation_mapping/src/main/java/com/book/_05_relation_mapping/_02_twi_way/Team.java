package com.book._05_relation_mapping._02_twi_way;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // 양방향 연관관계가 필요한 경우가 생긴다
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }
}
