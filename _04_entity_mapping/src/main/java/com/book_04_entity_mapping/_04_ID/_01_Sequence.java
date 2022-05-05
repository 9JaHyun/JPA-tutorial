package com.book_04_entity_mapping._04_ID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * ID는 기본적으로 JPA가 해당 엔티티를 다룰 때 사용할 식별자.
 *  - 추가적으로 이 식별자를 어떻게 다룰지에 대한 전략 설정을 할 수 있다.
 *      - SEQUENCE: SEQUENCE를 사용하는 RDBMS 에 이 방법을 사용 (ORACLE)
 *      - IDENTIFY: AUTO_INCREMENT 전략을 사용하는 RDBMS 에 사용 (MySQL)
 *
 * ID에 적용할 수 있는 타입
 *  - 자바 Primitive, Wrapper
 *  - String
 *  - BigDecimal, BigInteger
 */
@Setter @Getter
@SequenceGenerator(name = "idSeq", sequenceName = "ID_SEQ", initialValue = 1)
@Table(name = "TEST_SEQUENCE_TBL")
@Entity
public class _01_Sequence {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idSeq")
    private long id;

    private String name;

}
