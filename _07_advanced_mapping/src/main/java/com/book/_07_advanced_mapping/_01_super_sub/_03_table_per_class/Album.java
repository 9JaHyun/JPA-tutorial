package com.book._07_advanced_mapping._01_super_sub._03_table_per_class;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
@DiscriminatorValue("A")        // 부모 클래스에 구분 컬럼에 입력할 값 지정
public class Album extends Item {

    // 이 엔티티의 PK 가 바로 Item의 PK
    private String name;
}
