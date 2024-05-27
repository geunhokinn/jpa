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
            Address address1 = new Address("city", "street", "10000");
            Address address2 = new Address("city", "street", "10000");

            System.out.println("(address1 == address2) = " + (address1 == address2));
            System.out.println("address1.equals(address2) = " + (address1.equals(address2)));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}