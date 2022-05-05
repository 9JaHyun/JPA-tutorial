package com.example._06_variable_mapping._03_many_to_many;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Order {

    // 복합키로 만드는 것 보단, 이렇게 아예 상관이 없는 ID를 PK로 만들어 주는게 유연성이 생김.
    // 항상 Trade-Off를 고려하자.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // 비즈니스 로직 상 필요한 필드들
    private int count;
    private int price;
    private LocalDateTime orderDateTime;

}
