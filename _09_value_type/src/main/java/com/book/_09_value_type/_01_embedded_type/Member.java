package com.book._09_value_type._01_embedded_type;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

/**
 * 임베디드 타입
 *  - 새로운 값 타입을 직접 정의해서 사용하는 것을 임베디드 타입이라 함.
 *  - 해당 방법을 사용하면, 불필요한 연관관계를 대신할 수 있다.
 *      - 이는 논란의 소지가 많다.
 *      - 값 추적이 필요하기 때문에 값타입보다는 연관관계 매핑을 하는게 더 낫다 쪽과
 *      - 불필요한 연관관계를 막기 위해서는 값 타입을 적극적으로 활용해야 한다는 타입이 있다.
 *
 */
@Setter
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // 집주소를 다른 엔티티에서도 많이 사용된다면? -> 따로 타입을 만들자!
//    private String city;
//    private String street;
//    private String zipCode;
    @Embedded
    @AttributeOverrides({
          @AttributeOverride(name = "city", column = @Column(name = "HOME_CITY")),
          @AttributeOverride(name = "street", column = @Column(name = "HOME_STREET")),
          @AttributeOverride(name = "zipCode", column = @Column(name = "HOME_ZIPCODE")),
    })
    private Address homeAddress;

    // 같은 임베디드 타입을 저장하고 싶은 경우 @AttributeOverride 를 사용하자.
    @Embedded
    @AttributeOverrides({
          @AttributeOverride(name = "city", column = @Column(name = "COMPANY_CITY")),
          @AttributeOverride(name = "street", column = @Column(name = "COMPANY_STREET")),
          @AttributeOverride(name = "zipCode", column = @Column(name = "COMPANY_ZIPCODE")),
    })
    private Address workAddress;

    // 값 타입을 하나 이상 저장하려면 @ElementCollection 사용
    // 여러 값을 매핑하기때문에, DB 입장에서는 테이블을 하나 만들어야 한다. (@CollectionTable 사용)
    // 이런 값타입 컬렉션은 영속성 전이 + 고아 객체 제거 기능을 필수로 가짐. (추가적으로 지연 로딩도 가짐)
    @ElementCollection
    @CollectionTable(name = "WORK_HISTORY", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private List<WorkHistory> workHistoryList = new ArrayList<WorkHistory>();
}
