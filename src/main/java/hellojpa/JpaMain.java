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
            member.setUsername("member1");
            em.persist(member);

            // flush -> commit, query

            em.flush();

            // con.executeQuery("select * from member") -> jpa 와 관련 없는 기술

            List<Member> members = em.createQuery("select m from Member m where m.username like '%hello%'", Member.class).getResultList();
            for (Member m : members) {
                System.out.println("member = " + m);
            }
            
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}