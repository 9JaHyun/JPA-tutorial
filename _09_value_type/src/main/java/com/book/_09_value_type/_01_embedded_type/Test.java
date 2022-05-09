package com.book._09_value_type._01_embedded_type;

import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Test {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaSetup");
    public static void main(String[] args) {
        Test test = new Test();
//        test.testing1();
//        test.testing2();
//        test.testing3();
//        test.testing4();
        test.testing5();
    }

    public void testing1() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Member member = new Member();
        member.setName("회원1");
        Address address = new Address("도시1", "oo로", "oo길");
        member.setHomeAddress(address);
        em.persist(member);

        tx.commit();
    }

//    public void testing2() {
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//
//        tx.begin();
//
//        Member member1 = new Member();
//        member1.setName("회원1");
//        Address address = new Address();
//        address.setCity("도시1");
//        address.setStreet("oo로");
//        member1.setHomeAddress(address);
//        em.persist(member1);
//
//        // Addres 참조이기 때문에 회원1의 Address 역시 변경이 이루어 진다.
//        // 이를 해결하기 위한 방안
//        //  1. 참조가 아닌 복사를 하던가
//        //  2. 값 객체를 불변객체로 만들면 된다.
//        Member member2 = new Member();
//        Address newAddress = member1.getHomeAddress();
//        address.setCity("도시2");
//        member2.setName("회원2");
//        member2.setHomeAddress(newAddress);
//        em.persist(member2);
//
//        tx.commit();
//    }

    public void testing3() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Member member1 = new Member();
        member1.setName("회원1");
        Address address = new Address("도시1", "oo로", "oo길");
        member1.setHomeAddress(address);
        em.persist(member1);

        // Addres 참조이기 때문에 회원1의 Address 역시 변경이 이루어 진다.
        // 이를 해결하기 위한 방안
        //  1. 참조가 아닌 복사를 하던가
        //  2. 값 객체를 불변객체로 만들면 된다.
        Member member2 = new Member();
        member2.setName("회원2");
//        Address newAddress = member1.getHomeAddress();
//        newAddress.set() setter을 막아놔서 못함.
        Address address2 = new Address("도시2", "oo로", "oo길");
        member2.setHomeAddress(address2);
        em.persist(member2);

        tx.commit();
    }

    // 값 타입 컬렉션 사용
    public void testing4() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Member member1 = new Member();
        member1.setName("회원1");
        WorkHistory workHistory = new WorkHistory(LocalDateTime.now(), LocalDateTime.now());
        member1.getWorkHistoryList().add(workHistory);
        em.persist(member1);

        tx.commit();
    }


    // 임베디드 타입을 다른 테이블에 저장
    public void testing5() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Member2 member = new Member2();
        member.setName("회원1");
        Address2 address2 = new Address2("ㅁㅁ시", "ㅇㅇ로", "ㅁㅁ길");
        member.setAddress(
              address2);
        em.persist(member);

        tx.commit();
    }
}
