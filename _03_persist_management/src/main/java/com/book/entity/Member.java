package com.book.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Member {

    @Id
    private long id;
    private String name;

    public Member() {}

    public Member(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Member{" +
              "id=" + id +
              ", name='" + name + '\'' +
              '}';
    }
}
