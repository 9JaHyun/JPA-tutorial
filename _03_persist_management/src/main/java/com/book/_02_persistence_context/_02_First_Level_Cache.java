package com.book._02_persistence_context;

import com.book.entity.Member;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _02_First_Level_Cache {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaSetup");

    public static void main(String[] args) {
        _02_First_Level_Cache study = new _02_First_Level_Cache();
        study.testing();
    }

    /**
     * 이렇게 영속성 컨텍스트에서 관리를 하면 해당 엔티티가 또다시 필요한 경우에도 select 쿼리가 날아가지 않는다.
     * 이는 DB에서 검색하기 이전에 영속성 컨텍스트에서 해당 엔티티를 가지고 있는지 체크 후
     * 가지고 있다면 이를 전송하기 때문이다. 이를 1차캐시라고 한다.
     */
    private void testing() {
        EntityManager em = emf.createEntityManager();

        Member member = new Member(100L, "1차 캐시 테스트");

        // 영속 상태 진입
        em.persist(member);

        System.out.println("===== 기존대로라면 쿼리가 날아가야함 =====");
        Member findMember = em.find(Member.class, 100L);
        System.out.println("findMember = " + findMember);
        System.out.println("==========================================");

        // 준영속 테스트
        em.detach(member);

        System.out.println("===== 준영속 상태라면 쿼리가 날아갈까?? =====");
        Member findMember2 = em.find(Member.class, 100L);
        System.out.println("findMember2 = " + findMember2);
        System.out.println("==========================================");

        System.out.println("===== find 역시 영속상태이기 때문에 1차캐시에 영향을 받는다. =====");
        Member findMember3 = em.find(Member.class, 100L);
        System.out.println("findMember3 = " + findMember3);
        System.out.println("==========================================");

        System.out.println("===== 동일성 보장 =====");
        System.out.println("findMember2 == findMember3 ? : "  + (findMember2 == findMember3));
        System.out.println("findMember2 equals findMember3 ? : " + findMember2.equals(findMember3));

        em.close();
    }
}
