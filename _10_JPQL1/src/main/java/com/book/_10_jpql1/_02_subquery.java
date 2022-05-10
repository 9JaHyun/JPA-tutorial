package com.book._10_jpql1;

import com.book._10_jpql1.entity.Member;
import com.book._10_jpql1.entity.Order;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * JPA 서브 쿼리
 *  - 일반적인 서브쿼리 방식을 사용하면 됨
 *
 *  서브쿼리 지원 함수
 *      - EXIST [ALL | ANY | SOME]
 *      - IN
 *
 *  주의사항
 *      - JPA는 WHERE, HAVING 절에서만 서브 쿼리를 사용할 수 있다.
 *      - SELECT 절 서브쿼리는 하이버네이트에서 지원하고 있다.
 *      - FROM 절의 서브쿼리는 JPQL에서는 불가능.
 *          - 만약 조인으로 풀 수 있으면 이를 활용하자.
 */
public class _02_subquery {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaSetup");

    public static void main(String[] args) {
        _02_subquery subquery = new _02_subquery();

    }

    // 일반적인 서브쿼리 방식을 사용하면 된다.
    public void testing1() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member = new Member();
        member.setName("회원1");
        em.persist(member);

        // 이렇게 서브쿼리가 가능
        List<Member> memberList = em.createQuery(
                    "select m from Member m where m.name in (select m.name from Member m where m.id <= 10)",
                    Member.class)
              .getResultList();
    }

    /**
     * 서브 쿼리 지원 함수
     *  - EXIST
     *      [ALL | ANY | SOME] 키워드를 섞어 쓸 수 있다.
     *  - IN
     */
    public void testing2() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member = new Member();
        member.setName("회원1");
        em.persist(member);

        // IN
        List<Member> memberList = em.createQuery(
                    "select m from Member m where m.name in (select m.name from Member m where m.id <= 10)",
                    Member.class)
              .getResultList();

        // EXIST
        List<Member> teamAMembers = em.createQuery(
                    "select m from Member m where exists (select t from m.team t where t.name = '팀A')",
                    Member.class)
              .getResultList();

        // ALL : 모두를 만족하면 참  |  ANY, SOME : 조건을 하나라도 만족하면 참
        List<Order> orderList = em.createQuery(
                    // 전체 상품 각각의 재고보다 주문량이 많은 주문들
                    "select o from Order o where o.orderAmount > ALL(select p.stockAmount from Product p)",
                    Order.class)
              .getResultList();

        // ANY
        List<Member> members = em.createQuery(
                    "select m from Member m where m.team = any (select t from Team t)",
                    Member.class)
              .getResultList();
    }

    public void testing3() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member = new Member();
        member.setName("회원1");
        em.persist(member);

        // WHERE 절
        List<Member> memberList = em.createQuery(
                    "select m from Member m where m.name in (select m.name from Member m where m.id <= 10)",
                    Member.class)
              .getResultList();

        // SELECT 절
        List resultList = em.createQuery(
                    "select (select avg(m1.age) from Member m1 where m1.id <= 10) from Member m")
              .getResultList();

        // FROM 절은 불가능
//        List resultList = em.createQuery(
//                    "select mm.name, mm.age from (select m.name, m.age from Member m) as mm")
//              .getResultList();

    }
}
