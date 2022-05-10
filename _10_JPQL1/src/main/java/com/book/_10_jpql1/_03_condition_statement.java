package com.book._10_jpql1;

import com.book._10_jpql1.entity.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 조건식 - CASE : CASE 조건식은 일반적인 방법 그대로 사용하면 된다. - COALESCE : ORACLE의 NVL과 같은 식 - NULLIF: 두 값이 같으면
 * null, 다르면 첫번째 값 반환
 */
public class _03_condition_statement {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaSetup");

    public static void main(String[] args) {
        _03_condition_statement study = new _03_condition_statement();

    }

    // CASE 조건식
    public void testing1() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member = new Member();
        member.setName("회원1");
        em.persist(member);

        List resultList1 = em.createQuery("select "
                    + "CASE when m.age <= 10 then '학생요금'"
                    + "when m.age >= 60 then '경로요금'"
                    + "else '일반요금' end "
                    + "from Member m")
              .getResultList();

        // 단순 CASE 식
        List resultList2 = em.createQuery("select"
                    + " case t.name"
                    + " when '팀A' then '인센티브 110%'"
                    + " when '팀B' then '인센티브 120%'"
                    + " else '인센티브 105%'"
                    + " end"
                    + " from Team t")
              .getResultList();
    }
}
