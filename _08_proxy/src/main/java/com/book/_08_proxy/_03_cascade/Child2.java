package com.book._08_proxy._03_cascade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Child2 {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent2 parent;

    public void setParent(Parent2 parent) {
        this.parent = parent;
        if (!parent.getChildren().contains(this)) {
            parent.getChildren().add(this);
        }
    }
}
