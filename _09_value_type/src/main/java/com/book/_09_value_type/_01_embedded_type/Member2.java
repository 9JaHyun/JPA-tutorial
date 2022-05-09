package com.book._09_value_type._01_embedded_type;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
// @SecondaryTable 을 통해 다른 테이블에 저장된 데이터를 @Embeddable 로 매핑이 가능해진다.
@SecondaryTable(name = "address", pkJoinColumns = @PrimaryKeyJoinColumn(
      name = "member_id", referencedColumnName = "id"
))
@Entity
public class Member2 {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // 이후 @AttributeOverrides 를 통해 column table을 지정하자.
    @Embedded
    @AttributeOverrides({
          @AttributeOverride(name = "city", column = @Column(table = "address", name = "city")),
          @AttributeOverride(name = "street", column = @Column(table = "address", name = "street")),
          @AttributeOverride(name = "zipCode", column = @Column(table = "address", name = "zipcode"))
    })
    private Address2 address;
}