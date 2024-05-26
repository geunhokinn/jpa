package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("hello");

            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.getReference(Member.class, member.getId());
            System.out.println("findMember.getClass() = " + findMember.getClass());

//            em.detach(findMember);
//            em.close();
//            em.clear();

//            System.out.println(findMember.getUsername()); // 프록시 초기화
            Hibernate.initialize(findMember); // 강제 초기화

            // 준영속 상태이기 때문에 영속성 컨텍스트가 제공하는 기능을 사용하지 못함.
            // 버전이 올라가면서 트랜잭션이 유지되면 lazy 로딩을 사용할 수 있으므로 예외가 발생 안함.

            System.out.println("emf.getPersistenceUnitUtil().isLoaded(findMember) = " + emf.getPersistenceUnitUtil().isLoaded(findMember));
            
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
