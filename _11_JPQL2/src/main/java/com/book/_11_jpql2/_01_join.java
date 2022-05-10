package com.book._11_jpql2;

import com.book._11_jpql2.entity.Member;
import com.book._11_jpql2.entity.Team;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 조인
 *  - 내부 조인
 *      - JPA는 기본적으로 내부 조인을 따라간다.
 *  - 외부 조인
 *      - 외부 조인이 필요하면 명시적으로 외부 조인을 해 줘야 함.
 *  - 세타 조인
 */
public class _01_join {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaSetup");

    public static void main(String[] args) {
        _01_join study = new _01_join();
//        study.testing1();
//        study.testing2();
        study.testing3();
    }

    private void testing1() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Team team = new Team();
        team.setName("팀1");
        em.persist(team);

        Member member = new Member();
        member.setName("회원 1");
        member.setTeam(team);
        em.persist(member);

        // 기본적으로 INNER 조인
        List<Member> memberList = em.createQuery("select m from Member m join m.team t",
                    Member.class)
              .getResultList();
    }

    private void testing2() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Team team = new Team();
        team.setName("팀1");
        em.persist(team);

        Member member = new Member();
        member.setName("회원 1");
        member.setTeam(team);
        em.persist(member);

        // 외부조인을 하고 싶으면 명시적으로 OUTER JOIN을 명시하자
        List<Member> memberList = em.createQuery("select m from Member m LEFT OUTER join m.team t",
                    Member.class)
              .getResultList();
    }

    private void testing3() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Team team = new Team();
        team.setName("팀1");
        em.persist(team);

        Member member = new Member();
        member.setName("회원 1");
        member.setTeam(team);
        em.persist(member);

        // 세타 조인
        List resultList = em.createQuery("select m from Member m, Team t where m.team.id = t.id")
              .getResultList();
    }

    // 외부 조인을 사용하는 ON 절
    private void testing4() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Team team = new Team();
        team.setName("팀1");
        em.persist(team);

        Member member = new Member();
        member.setName("회원 1");
        member.setTeam(team);
        em.persist(member);

        // 세타 조인
        List<Member> memberList = em.createQuery("select m from Member m LEFT join m.team t on t.name = '팀1'",
                    Member.class)
              .getResultList();
    }
}
