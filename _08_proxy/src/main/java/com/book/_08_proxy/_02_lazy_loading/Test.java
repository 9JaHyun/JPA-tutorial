package com.book._08_proxy._02_lazy_loading;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 지연로딩
 *  - 필요한 값만 로딩 -> 이후 추가적으로 조인해야 할 값이 필요할 떄 마다 쿼리를 날림.
 *  - 정말 여러 엔티티들이 연관되어 있는 객체의 경우 효과적
 *
 *  주의점
 *      - 만능이 아니다.
 *      - 오히려 같이 값을 들고 올 때가 더 효과적일 떄가 있다.
 *      - 즉시 로딩과 지연 로딩은 비즈니스 로직을 잘 살펴본 후 필요에 따라 나누자.
 *      - 컬렉션을 로딩하는 경우에는 지연 로딩을 사용하라
 *      - 컬렉션 즉시 로딩은 항상 OUTER JOIN 을 사용한다.
 */
public class Test {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaSetup");

    public static void main(String[] args) {
        Test test = new Test();
        test.printUserAndTeam(1L);
    }

    // Team의 정보가 필요한 메서드
    public void printUserAndTeam(Long memberId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        setupData(em);
        tx.commit();
        em.clear();

        System.out.println("=== INIT MEMBER PROXY ===");
        Member2 member = em.getReference(Member2.class, memberId);
        Team2 team = member.getTeam();
        System.out.println("=== Member 로딩 O / Team 로딩 X ===");
        System.out.println("회원 이름 : " + member.getUsername());
        System.out.println("=== Team 로딩 시작 O ===");
        System.out.println("팀   이름 : " + team.getName());
        em.close();
    }

    // Team의 정보가 필요 없는 메서드
    public void printUser(Long memberId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        setupData(em);
        tx.commit();
        em.clear();

        Member2 findMember = em.find(Member2.class, memberId);
        System.out.println("회원 이름 : " + findMember.getUsername());
        em.close();
    }

    public void printUserV2(Long memberId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        setupData(em);
        tx.commit();
        em.clear();

        Member2 member = em.getReference(Member2.class, memberId);
        System.out.println("회원 이름 : " + member.getUsername());
        em.close();
    }

    // 초기 조건: flush 이후 영속성 컨텍스트 비우기
    private void setupData(EntityManager em) {
        Member2 member = new Member2();
        member.setUsername("회원1");
        Team2 team = new Team2();
        team.setName("팀1");
        em.persist(team);

        member.setTeam(team);
        em.persist(member);
    }
}
