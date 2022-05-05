package com.book_04_entity_mapping._04_ID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 * TABLE 전략 : 키 생성 전용 테이블을 만드는 방식 (SEQUENCE 방식과 동일)
 */

@TableGenerator(name = "idTable", table = "ID_TABLE",
                pkColumnValue = "TABLE_SEQ", initialValue = 1)
@Entity
public class _03_Table {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "idTable")
    private long id;

    private String name;
}
