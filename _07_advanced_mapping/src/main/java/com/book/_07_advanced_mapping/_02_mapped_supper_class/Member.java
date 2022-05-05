package com.book._07_advanced_mapping._02_mapped_supper_class;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
// 이렇게 각 엔티티마다 테이블 속성 명을 수정할 수 있다.

@AttributeOverrides({
      @AttributeOverride(name = "createdAt", column = @Column(name = "register_date")),
      @AttributeOverride(name = "createdBy", column = @Column(name = "register_Info")),
      @AttributeOverride(name = "updatedAt", column = @Column(name = "change_info_date")),
      @AttributeOverride(name = "updatedBy", column = @Column(name = "updated_Info"))
})
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;
}
