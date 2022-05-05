package com.example._06_variable_mapping._03_many_to_many;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;

// 일대일 관계에서의 핵심은 어디에 연관관계 주인을 설정할지? 이다.
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // ManyToMany를 지원(이러면 JPA가 자동으로 1:N, N:1인 구조를 가지는 테이블을 하나 더 만들어 줌
    // 하지만 잘 사용하지 않는다. => 비즈니스 상으로 추가적인 필드가 필요할 수 있다.
    // 그래서 엔티티를 직접 만드는 편.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
