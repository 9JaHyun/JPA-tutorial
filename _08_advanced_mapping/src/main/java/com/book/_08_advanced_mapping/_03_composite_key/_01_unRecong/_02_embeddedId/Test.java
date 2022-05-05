package com.book._08_advanced_mapping._03_composite_key._01_unRecong._02_embeddedId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaSetup");

    public static void main(String[] args) {
        Test test = new Test();
        test.testing();
    }

    public void testing() {
        EntityManager em = emf.createEntityManager();

        Parent2 parent = new Parent2();
        ParentId2 parentId2 = new ParentId2("id1", "id2");

        parent.setParentId(parentId2);
        parent.setName("테스트 이름");

        em.persist(parent);

        ParentId2 findParentId2 = new ParentId2("parentId1", "parentId2");
        Parent2 findParent = em.find(Parent2.class, findParentId2);

        System.out.println(parent.equals(findParent));
    }


}
