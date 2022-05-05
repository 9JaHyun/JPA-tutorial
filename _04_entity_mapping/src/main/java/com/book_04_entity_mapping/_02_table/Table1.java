package com.book_04_entity_mapping._02_table;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Table은 기본적으로 엔티티와 매핑할 테이블을 지정
 */
@Table(name = "entity_table"
      //, catalog = 카탈로그 기능이 있는 DB에서 사용
      //, schema = shcema 기능이 있는 DB에서 사용
      //,uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})} DDL 생성 시 필요한 제약조건
)
@Entity
public class Table1 {

    @Id
    private long id;
    private String name;
}
