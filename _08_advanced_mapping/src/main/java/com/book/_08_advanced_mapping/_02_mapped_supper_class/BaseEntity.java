package com.book._08_advanced_mapping._02_mapped_supper_class;

import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;
import lombok.Getter;

/**
 * @MappedSuperClass
 *  - 때때로 여러 테이블들이 공통적으로 필요한 속성이 있을 수 있다.
 *  - 이런 경우 다음 방법과 같이 자식에게 해당 정보를 제공할 수 있다.
 *  - 물려 받은 속성들을 재정의하고 싶으면, 해당 엔티티에서 @AttributeOverrides 사용하자.
 *
 *  단점
 *      - 자바는 단일 상속을 따르기 때문에 다른 상속이 있으면 골치아파짐.
 */

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
