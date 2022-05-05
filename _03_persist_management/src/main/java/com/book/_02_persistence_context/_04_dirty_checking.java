package com.book._02_persistence_context;

import com.book.entity.Member;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class _04_dirty_checking {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaSetup");

    public static void main(String[] args) {
        _04_dirty_checking study = new _04_dirty_checking();
        study.testing();
    }

    private void testing() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Member member = new Member(55L, "55번째 회원");

        System.out.println("===== member을 영속성 컨텍스트에 등록");
        em.persist(member);

        System.out.println("===== member DB에 저장 =====");
        em.flush();

        System.out.println("member 수정");
        member.setName("새로운 55번째 회원");

        System.out.println("===== COMMIT =====");
        em.flush();

        System.out.println("find() 메서드 실행 : flush() 이후 영속성 컨텍스트의 엔티티를 비울까?");
        Member findMember = em.find(Member.class, 55L);
        // 비우지 않는다! => DB와 동기화할 뿐
        System.out.println(findMember);
        transaction.commit();
        em.close();
    }
}
