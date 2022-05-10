package com.book._10_jpql1;

import com.book._10_jpql1.entity.Address;
import com.book._10_jpql1.entity.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class _01_basic {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaSetup");

    public static void main(String[] args) {
        _01_basic basic = new _01_basic();
        basic.testing3();
    }

    public void testing1() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member = new Member();
        member.setName("회원1");
        em.persist(member);

        // 별칭은 필수적으로 사용해야 한다.
        TypedQuery<Member> query = em.createQuery("select m from Member m where m.id = 1",
              Member.class);

        // getResultList() : 결과가 없으면 빈 리스트 반환 => nullPoint 걱정 안해도 됨
        List<Member> memberList = query.getResultList();

        // getSingleResult : 결과가 정확히 하나일때만 사용. 익셉션에 유의
        //      NoResultException : 결과가 없으면 발생
        //      NonUniqueResultException : 둘 이상이면 발생
        Member singleResult = query.getSingleResult();
    }

    // 파라미터 바인딩
    public void testing2() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member = new Member();
        member.setName("회원1");
        em.persist(member);

        em.flush();
        em.clear();

        // 이름 기준으로 매핑방법 (해당 방식을 많이 사용)
        TypedQuery<Member> query1 = em.createQuery("select m from Member m where m.name =: name",
              Member.class);
        query1.setParameter("name", "회원1");

        // 위치 기준으로 매핑 (이름 기준보다는 잘 쓰이지 않음.)
        TypedQuery<Member> query2 = em.createQuery("select m from Member m where m.name = ?1",
              Member.class);
        query2.setParameter(1, "회원1");

        tx.commit();

    }

    // 여러가지 프로젝션 (SELECT)
    public void testing3() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member = new Member();
        member.setName("회원1");
        em.persist(member);

        em.flush();
        em.clear();

        // 엔티티 프로젝션 : 영속성 컨텍스트가 관리해주는 이점이 있다.
        List<Member> memberList = em.createQuery("select m from Member m", Member.class)
              .getResultList();

        // 임베디드 프로젝션 : 임베디드 혼자만으로는 프로젝션이 불가능함. 엔티티에서 가져와야 한다.
        //                     또한 엔티티가 아니기 때문에, 영속성 컨텍스트에서 관리를 못함.
//        em.createQuery("select a from Address a", Address.class);
        List<Address> addressList = em.createQuery("select o.address from Order o", Address.class)
              .getResultList();

        // 여러 값 프로젝션 : 이 경우에는 딱히 타입이 있는 것이 아니기 때문에, 제네릭을 사용하지 않음.
        List<Object[]> resultList = em.createQuery("select m.name, m.age from Member m")
              .getResultList();

        // Object[] 방식을 사용하면, getter()를 사용할 필요 없이 쉽게 추출이 가능하다.

        for (Object[] objects : resultList) {
            String name = (String) objects[0];
            Integer age = (Integer) objects[1];
            System.out.println("이름 : " + name + " / 나이 : " + age);
        }

        // 여러 값을 클래스로 매핑 : DTO를 따로 만들 경우에는 new 생성자 방법으로 매핑이 가능하다.
        List<MemberInfoDTO> dtoList = em.createQuery(
                    "select new com.book._10_jpql1.MemberInfoDTO(m.name, m.age) from Member m",
                    MemberInfoDTO.class)
              .getResultList();

        tx.commit();
    }

    // 페이징 기능
    // 강력한 기능 중 하나가 페이징 => Oracle 페이징이 얼마나 악랄한지 알 것이다!
    // 만약 페이징 기능의 최적화가 필요하다면 네이티브 SQL을 활용해야 한다.
    public void testing4() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        for (int i = 0; i < 100; i++) {
            Member member = new Member();
            member.setName("회원" + i);
            em.persist(member);
        }

        em.flush();
        em.clear();
    }
}
