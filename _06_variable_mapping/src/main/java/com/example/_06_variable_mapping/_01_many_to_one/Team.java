package com.example._06_variable_mapping._01_many_to_one;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;

@Getter
@Entity
public class Team {

    @Id
    @Column(name = "team_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // 양방향 연관관계를 맺기 위해서 OneToMany 사용
    // 이때 연관관계의 주인(외래키를 가지는 쪽)은 Member 이기 때문에 mappedBy를 사용한다
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    public void addMember(Member member) {
        this.members.add(member);

        // 무한루프 방지
        if (member.getTeam() != this) {
            member.changeTeam(this);
        }
    }
}
