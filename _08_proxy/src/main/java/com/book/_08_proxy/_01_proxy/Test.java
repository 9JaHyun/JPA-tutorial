package com.book._08_proxy._01_proxy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Test {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaSetup");

    public static void main(String[] args) {
        Test test = new Test();
        // 만약 Team의 정보가 매우 방대하다면, 사용하지도 않을 Team을 검색한다고 너무 오래 걸리지 않을까?
        // 최적화 방안? -> 필요할때 땡겨쓰자! (프록시의 지연로딩)
//        test.printUser(1L);
//        test.printUserV2(1L);
//        test.printUserAndTeam(1L);
//        test.proxyTest();
//        test.proxyTest2();
//        test.proxyTest3();
//        test.proxyTest4();
        test.proxyTest5();
    }

    // Team의 정보가 필요한 메서드
    public void printUserAndTeam(Long memberId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        setupData(em);
        tx.commit();
        em.clear();

        Member member = em.find(Member.class, memberId);
        Team team = member.getTeam();
        System.out.println("회원 이름 : " + member.getUsername());
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

        Member findMember = em.find(Member.class, memberId);
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

        Member member = em.getReference(Member.class, memberId);
        System.out.println("회원 이름 : " + member.getUsername());
        em.close();
    }

    public void proxyTest() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Member member = new Member();
        member.setUsername("회원1");
        Team team = new Team();
        team.setName("팀1");
        em.persist(team);

        member.setTeam(team);
        em.persist(member);
        tx.commit();
        em.clear();

        System.out.println("==== first getReference() ====");
        Member proxyMember1 = em.getReference(Member.class, 1L);
        // 같은 엔티티를 검색했다는 조건하에 두번쨰는 1차 캐시에서 꺼낸다.
        System.out.println("==== second getReference() ==== ");
        Member proxyMember2 = em.getReference(Member.class, 1L);

        // 엔티티를 조회하면 의도하지 않아도 무조건 DB에 쿼리가 날아간다.
        System.out.println("Proxy1의 클래스 : " + proxyMember1);
        System.out.println("Proxy2의 클래스 : " + proxyMember2);
        System.out.println("Proxy, proxy2 same(==)? " + (proxyMember1 == proxyMember2));    // true
        // Proxy는 엔티티 자체가 아닌, 엔티티를 상속받아 만들어지는 객체이다. (참조값만 가짐)
        // 그래서 equals()는 틀리고, instanceof만 먹히게 된다.
        System.out.println("Proxy, entity equals()? " + proxyMember1.equals(member));       // false
        System.out.println("Proxy, entity instanceof? " + (proxyMember1 instanceof Member));  // true
    }

    public void proxyTest2() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        setupData(em);
        tx.commit();
        em.clear();

        System.out.println("==== Reference 검색 시 참조값만 가지고, 실제 DB 조회는 하지 않는다. ====");
        Member findMember = em.getReference(Member.class, 1L);
        System.out.println("=== 실제 사용될 때 DB 조회 후 엔티티 객체 생성 ===");
        System.out.println("회원이름 = " + findMember.getUsername());
        System.out.println("==== 나머지 Team도 조회 실시(현재는 Lazy Loading 비활성화로 안먹힘) ====");
        System.out.println("팀 이름 = " + findMember.getTeam().getName());
    }

    public void proxyTest3() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        setupData(em);
        tx.commit();

        // 영속성 컨텍스트에 로딩 (현재는 IDENTITY 전략이기 때문에 재조회를 해야 함.)
        em.find(Member.class, 1L);

        System.out.println("==== 만약 영속성컨텍스트에 해당 엔티티가 이미 있다면, 그 엔티티를 반환. ====");
        Member findMember = em.getReference(Member.class, 1L);
        System.out.println("findMember.getUsername = " + findMember.getUsername());;
    }

    public void proxyTest4() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        setupData(em);
        tx.commit();
        em.clear();

        System.out.println("==== JPA가 더 이상 관리하지 않는 프록시를 초기화하면, LazyInitializationException 예외 발생. ====");
        Member findMember = em.getReference(Member.class, 1L);
        em.close();
        System.out.println("findMember.getUsername = " + findMember.getUsername());;
    }

    public void proxyTest5() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        setupData(em);
        tx.commit();
        em.clear();

        System.out.println("==== 초기화 여부 확인 ====");
        Member findMember = em.getReference(Member.class, 1L);
        System.out.println("findMember init? : " + emf.getPersistenceUnitUtil().isLoaded(findMember));  // false
        System.out.println("findMember.getUsername = " + findMember.getUsername());;
        System.out.println("findMember init? : " + emf.getPersistenceUnitUtil().isLoaded(findMember));  // true
    }

    // 초기 조건: flush 이후 영속성 컨텍스트 비우기
    private void setupData(EntityManager em) {
        Member member = new Member();
        member.setUsername("회원1");
        Team team = new Team();
        team.setName("팀1");
        em.persist(team);

        member.setTeam(team);
        em.persist(member);
    }
}
