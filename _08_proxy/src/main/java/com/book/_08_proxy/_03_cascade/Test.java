package com.book._08_proxy._03_cascade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Test {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaSetup");

    public static void main(String[] args) {
        Test test = new Test();
//        test.testing();
//        test.testing2();
        test.testing3();
    }

    public void testing() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 부모가 영속상태가 이니기 때문에 오류 발생.
        // 이런 경우 부모만 영속으로 두면 나머지도 따라오는 영속성 전이 기능을 사용할 수 있다.
        Parent parent = new Parent();
//        em.persist(parent);

        Child child1 = new Child();
        child1.setParent(parent);
        em.persist(child1);

        Child child2 = new Child();
        child2.setParent(parent);
        em.persist(child2);

        tx.commit();
    }

    public void testing2() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 영속성 전이 기능으로 자식 엔티티들을 영속성 컨텍스트에 등록하지 않아도 문제가 생기지 않는다.
        Parent2 parent = new Parent2();

        Child2 child1 = new Child2();
        child1.setParent(parent);

        Child2 child2 = new Child2();
        child2.setParent(parent);
        em.persist(parent);

        tx.commit();
    }

    public void testing3() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 영속성 전이 기능으로 자식 엔티티들을 영속성 컨텍스트에 등록하지 않아도 문제가 생기지 않는다.
        Parent2 parent = new Parent2();

        Child2 child1 = new Child2();
        child1.setParent(parent);

        Child2 child2 = new Child2();
        child2.setParent(parent);
        em.persist(parent);
        em.flush();
        em.clear();

        Parent2 findParent = em.find(Parent2.class, 1L);
        // orphanRemoval, 부모 엔티티 삭제 시 참조되는 자식 엔티티 역시 삭제
        em.remove(findParent);

        tx.commit();
    }
}
