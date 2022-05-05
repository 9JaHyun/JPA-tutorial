package com.book._05_relation_mapping._02_twi_way;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class _01_N_by_1 {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaSetup");

    public static void main(String[] args) {
        _01_N_by_1 study = new _01_N_by_1();
        study.testing1();
        study.testing2();
    }

    public void testing1() {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        Team team1 = new Team("team1");
        em.persist(team1);

        member1.setTeam(team1);
        member2.setTeam(team1);

        em.persist(member1);
        em.persist(member2);

        tx.commit();
        em.close();

    }

    public void testing2() {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Team team2 = new Team("team2");
        em.persist(team2);

        Member findMember = em.find(Member.class, 2L);
        findMember.setTeam(team2);
        tx.commit();
        em.close();
    }
}
