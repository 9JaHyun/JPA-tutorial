package com.book._01_entity_manger_factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 공장을 세우는데에는 비용이 많이 드나, 세운 뒤 물건을 찍어내는데에는 비교적 적게 든다.
 *  - 공장 : EntityManagerFactory
 *  - 물건 : EntityManager
 *
 *  이때 공장의 설계도는 Persistence.xml 을 따른다.
 *  EntityManager은 DB 커넥션과 밀접한 관계가 있기 때문에 절대 스레드 간 공유하거나 재사용해서는 안된다.
 *  추가적으로 close()를 통해 메모리 관리를 하자.
 */
public class _01_MangerFactory {

    public static void main(String[] args) {
        // Persistence.xml 에서 설정해놓은 persistence-unit name을 따른다. (비용이 많이 듬)
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("jpaSetup");

        // 그 후 Factory로 부터 엔티티 매니저를 생성 (비용이 거의 안듬)
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManager.close();
    }

}
