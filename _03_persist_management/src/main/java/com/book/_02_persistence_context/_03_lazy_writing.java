package com.book._02_persistence_context;

import com.book.entity.Member;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class _03_lazy_writing {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaSetup");


    public static void main(String[] args) {
        _03_lazy_writing study = new _03_lazy_writing();
        study.testing3();
    }

    private void testing() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        Member memberA = new Member(2L, "회원A");
        Member memberB = new Member(3L, "회원B");

        // persist했다고 바로 insert 되지는 않음. 쓰기 지연 저장소에 담아놓는다.
        em.persist(memberA);
        em.persist(memberB);

        System.out.println("===== Commit =====");
        transaction.commit();

        em.close();
    }

    private void testing2() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        Member memberA = new Member(2L, "회원A");
        Member memberB = new Member(3L, "회원B");

        // persist했다고 바로 insert 되지는 않음. 쓰기 지연 저장소에 담아놓는다.
        em.persist(memberA);
        em.persist(memberB);

        System.out.println("===== flush =====");
        em.flush();

        // commit 을 하면 flush가 되는 형식이기 때문에 미리 flush를 했다면 작동 X
        System.out.println("===== commit =====");
        transaction.commit();

        em.close();
    }

    private void testing3() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        Member memberA = new Member(2L, "회원A");
        Member memberB = new Member(3L, "회원B");

        // persist했다고 바로 insert 되지는 않음. 쓰기 지연 저장소에 담아놓는다.
        em.persist(memberA);
        em.persist(memberB);

        System.out.println("===== flush =====");
        em.flush();

        // commit 을 하면 flush가 되는 형식이기 때문에 미리 flush를 했다면 작동 X
        System.out.println("===== commit =====");
        transaction.rollback();

        em.close();
    }
}
