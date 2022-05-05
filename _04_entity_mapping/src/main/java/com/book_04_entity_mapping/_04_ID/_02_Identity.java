package com.book_04_entity_mapping._04_ID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * IDENTITY
 *  - AUTO_INCREMENT 기능을 가지는 RDBMS 에 사용되는 전략 (ID 생성을 DB에 위임.
 *  - ID가 없는 상태에서 flush -> DB에서 생성을 해 준 뒤 -> 영속성 컨텍스트에 업데이트
 */
@Setter
@Getter
@Table(name = "TEST_IDENTITY_TBL")
@Entity
public class _02_Identity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
}
