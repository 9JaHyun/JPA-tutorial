package com.book_04_entity_mapping._04_ID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class id_demo {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaSetup");

    public static void main(String[] args) {
        id_demo study = new id_demo();

//        study.sequence();
        study.sequence2();
//        study.identity();
//        study.identity2();
    }

    private void sequence() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        _01_Sequence sequence = new _01_Sequence();
        sequence.setName("Sequence1");

        em.persist(sequence);
        System.out.println("persist() 후 Sequence의 ID = " + sequence.getId());

        em.flush();
        System.out.println("flush() 후 Sequence의 ID = " + sequence.getId());

        tx.commit();
        System.out.println("commit() 후 Sequence의 ID = " + sequence.getId());

        em.close();
    }

    private void sequence2() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        for (int i = 0; i < 100; i++) {
            _01_Sequence sequence = new _01_Sequence();
            sequence.setName("Sequence1");

            em.persist(sequence);
            System.out.println("persist() 후 Sequence의 ID = " + sequence.getId());
        }
        tx.commit();

        em.close();
    }

    // IDENTITY 방식의 경우에는 RDBMS 가 ID를 직접 생성하기 때문에 INSERT를 먼저 날려야 ID 값을 알 수 있다.
    private void identity() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        _02_Identity identity = new _02_Identity();
        identity.setName("Identity1");

        em.persist(identity);
        System.out.println("persist() 후 Identity의 ID = " + identity.getId());

        em.flush();
        System.out.println("flush() 후 Identity의 ID = " + identity.getId());

        tx.commit();
        System.out.println("commit() 후 Identity의 ID = " + identity.getId());

        em.close();
    }



    private void identity2() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        for (int i = 0; i < 100; i++) {
            _02_Identity identity = new _02_Identity();
            identity.setName("Identity1");

            em.persist(identity);
            System.out.println("persist() 후 Identity의 ID = " + identity.getId());
        }
        em.flush();

        tx.commit();

        em.close();
    }
}
