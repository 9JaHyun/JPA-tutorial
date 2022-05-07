package com.book._08_proxy._03_cascade;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Parent2 {

    @Id
    @GeneratedValue
    private Long id;

    // 일대다에 많이 건다. (오직 하나의 부모 엔티티가 관리할 때만 사용)
    // orphanRemoval: 추가적으로 연고나관계가 끊어질 때 자식 엔티티를 자동으로 삭제하는 기능을 제공한다.
    // 두 기능 모두 참조하는 곳이 단 하나일 때 사용해야 한다. (게시판 - 첨부파일 관계)
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child2> children = new ArrayList<Child2>();

    private void addChildren(Child2 child) {
        this.children.add(child);

        if (child.getParent() != this) {
            child.setParent(this);
        }
    }
}
