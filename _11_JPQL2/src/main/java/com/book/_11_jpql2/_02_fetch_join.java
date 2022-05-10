package com.book._11_jpql2;

import com.book._11_jpql2.entity.Member;
import com.book._11_jpql2.entity.Team;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 페치조인
 *  - SQL의 종류가 아닌 JPQL 에서 성능 최적화를 위해 제공하는 기능임.
 *
 *  한계
 *      - 페치 조인 대상에는 별칭을 줄 수 없다. (주어서도 안된다.)
 *      - 둘 이상의 컬렉션은 페치 조인을 주의해야 한다.
 *          - 사용이 가능하나 데이터 정확성이 떨어짐.
 *          - 정 사용하려거든 `default_batch_fetch_size` 옵션을 사용해보자.
 *          - https://jojoldu.tistory.com/457 참고
 *      - 컬렉션을 페치 조인 하면 페이징 API (setFirstResult, setMaxResult)를 사용할 수 없다.
 *          - ToOne 은 가능함. (N+1 문제가 발생하지 않기 때문)
 *
 */
public class _02_fetch_join {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaSetup");

    public static void main(String[] args) {
        _02_fetch_join study = new _02_fetch_join();
//        study.testing1();
//        study.testing2();
        study.testing3();
    }

    private void testing1() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        setupData(em);

        List<Member> memberList = em.createQuery("select m from Member m", Member.class)
              .getResultList();

        // LAZY의 경우 Team1, Team2를 로드할 때 마다 SELECT 쿼리가 날아가는 것을 확인할 수 있다.
        System.out.println("====== 검색 시작 ======");
        for (Member m : memberList) {
            System.out.println("회원목록: " + m.getName() + ", " + m.getTeam().getName());
        }

        tx.commit();
    }

    private void testing2() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        setupData(em);

        // 이 경우에는 한번에 모든 데이터를 가져옴.
        List<Member> memberList = em.createQuery("select m from Member m join fetch m.team", Member.class)
              .getResultList();

        System.out.println("====== 검색 시작 ======");
        for (Member m : memberList) {
            System.out.println("회원목록: " + m.getName() + ", " + m.getTeam().getName());
        }

        em.clear();

        // 이는 컬렉션에서 더 큰 효과를 발휘
       List<Team> teamList = em.createQuery("select t from Team t join fetch t.members", Team.class)
              .getResultList();

        System.out.println("====== 검색 시작 ======");
        for (Team t : teamList) {
            System.out.println("팀 목록: " + t.getName() + ", " + t.getMembers().size());
        }

        tx.commit();
    }

    // 컬렉션 조인(OneToMany) 주의점: 카테시안 곱 문제가 발생 될 수 있음. (이를 보통 N+1 문제라고 한다.)
    //                     ManyToOne의 경우에는 이 문제가 생기지 않음.
    private void testing3() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        setupData(em);

        // 첫번째 방법으로는, 컬렉션에 List 대신 중복을 허용하지 않는 set을 사용
        // 두번째 방법으로는, distinct 를 사용하는 것. (컬렉션이 List 가 더 적합하다 생각될 경우)
        //  - JPQL의 DISTINCT : 일반적인 SQL의 distinct 기능 + 엔티티 중복도 추가로 제거

        List<Team> teamList = em.createQuery("select distinct t from Team t join fetch t.members", Team.class)
              .getResultList();

        System.out.println("====== 검색 시작 ======");
        for (Team t : teamList) {
            System.out.println("팀 목록: " + t.getName() + ", " + t.getMembers().size());
        }

        tx.commit();
    }

    private void setupData(EntityManager em) {
        Team team = new Team();
        team.setName("팀1");
        em.persist(team);

        Team team2 = new Team();
        team2.setName("팀2");
        em.persist(team2);

        Member member = new Member();
        member.setName("회원");
        member.setTeam(team);
        em.persist(member);
        Member member1 = new Member();
        member1.setName("회원1");
        member1.setTeam(team);
        em.persist(member1);
        Member member2 = new Member();
        member2.setName("회원2");
        member2.setTeam(team2);
        em.persist(member2);

        em.flush();
        em.clear();
    }
}
