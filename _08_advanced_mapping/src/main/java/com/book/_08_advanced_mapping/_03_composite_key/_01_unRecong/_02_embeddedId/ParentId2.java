package com.book._08_advanced_mapping._03_composite_key._01_unRecong._02_embeddedId;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

// @Embeddable 를 사용해야 Parent 에서 @EmbeddedId 를 사용할 수 있다.
// 추가적으로 Serializable를 구현해야 한다.
@Embeddable
public class ParentId2 implements Serializable {

    @Column(name = "PARENT_ID1")
    private String id1;

    @Column(name = "PARENT_ID2")
    private String id2;

    public ParentId2() {
    }

    public ParentId2(String id1, String id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParentId2 parentId = (ParentId2) o;
        return Objects.equals(id1, parentId.id1) && Objects.equals(id2,
              parentId.id2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id1, id2);
    }
}
