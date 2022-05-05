package com.book._07_advanced_mapping._03_composite_key._01_unRecong._01_idclass;

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

        Parent parent = new Parent();
        parent.setId1("parentId1");
        parent.setId2("parentId2");
        parent.setName("테스트 이름");

        em.persist(parent);

        ParentId parentId = new ParentId("parentId1", "parentId2");
        Parent findParent = em.find(Parent.class, parentId);

        System.out.println(parent.equals(findParent));
    }


}
