package com.book._02_persistence_context;

import com.book.entity.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * 영속성 컨텍스트 : 엔티티를 영구적으로 저장하는 환경
 *
 * 엔티티의 상태
 *  - 비영속 : 아예 영속성 컨텍스트에 등록이 안된 상태
 *  - 영속 : 영속성 컨텍스트에 등록이 되어 영속성 컨텍스트가 관리중인 상태
 *  - 준영속 : 이전에 영속성 컨텍스트에 등록되어있다가 현재는 관리를 하지 않는 상태
 *  - 삭제 : 영속성 컨텍스트에서 삭제된 상태
 *
 * 그럼... 영속관리가 뭐가 그래 중요한데?
 *  - 1차 캐시
 *  - 동일성 보장
 *  - 트랜잭션을 지원하는 쓰기지연
 *  - 변경 감지
 *  - 지연 로딩
 */
public class _01_Persistence_Flow {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaSetup");

    public static void main(String[] args) {
        _01_Persistence_Flow study = new _01_Persistence_Flow();
        study.testing1();
    }

    // 영속상태로 만들기
    private void testing1() {
        EntityManager em = emf.createEntityManager();
        Member member = new Member(1, "회원1");

        // 영속성 컨텍스트에 관리 시키는 조건
        // 1. 완전 새롭게 생성된 객체의 경우 persist()
        em.persist(member);

        // 2. DB에서 꺼내는 경우(1) find()
        em.find(Member.class, 1);

        // 2-1. DB에서 꺼내는 경우(2) JPQL 사용
        TypedQuery<Member> selectMember = em.createQuery("select m from Member m", Member.class);
        List<Member> resultList = selectMember.getResultList();

        // 준영속상태로 만들기

        // 1. 특정 엔티티 준영속상태로 만들기
        em.detach(member);

        // 2. 전체 엔티티 준영속상태로 만들기
        em.clear();

        // 엔티티 메니저를 종료하는 것 역시 준영속 상태로 만드는것이다.
        em.close();
    }

    // 준영속 상태로 만들기
    private void testing2() {
        EntityManager em = emf.createEntityManager();
        Member member = new Member(33L, "33번째 회원");

        System.out.println("=== persist member ===");
        em.persist(member);

        // 준영속 상태로 만들기
        // 식별자 값을 가지고 있다.
        System.out.println("=== detach member ===");
        em.detach(member);

        // 새로운 영속상태의 Member 엔티티를 반환
        Member mergeMember = em.merge(member);
    }
}
