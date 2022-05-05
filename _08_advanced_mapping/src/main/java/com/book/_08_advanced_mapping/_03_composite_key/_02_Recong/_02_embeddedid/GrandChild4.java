package com.book._08_advanced_mapping._03_composite_key._02_Recong._02_embeddedid;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class GrandChild4 {

    @EmbeddedId
    private GrandChildId4 id4;

    //
    @MapsId("childId4")
    @ManyToOne
    @JoinColumns({
          @JoinColumn(name = "PARENT_ID"),
          @JoinColumn(name = "CHILD_ID")
    })
    private Child4 child4;

    private String name;
}
