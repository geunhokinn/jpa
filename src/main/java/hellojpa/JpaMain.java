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
            member.setHomeAddress(new Address("homeCity", "street", "10000")); // 임베디드 타입

            member.getFavoriteFoods().add("치킨"); // 값 타입 컬렉션
            member.getFavoriteFoods().add("족발"); // 값 타입 컬렉션
            member.getFavoriteFoods().add("피자"); // 값 타입 컬렉션

            member.getAddressHistory().add(new AddressEntity("old1", "street", "10000"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "10000"));
            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("==============");
            Member findMember = em.find(Member.class, member.getId());

//            List<Address> addressHistory = findMember.getAddressHistory(); // 값 타입 컬렉션은 지연 로딩이다.
//            for (Address address : addressHistory) {
//                System.out.println("address.getCity() = " + address.getCity());
//            }

            // homeCity -> newCity // 값 타입
//            findMember.getHomeAddress().setCity("newCity");
            // 지금은 private 으로 막아서 안되는데 허용하면 모든 인스턴스가 바뀌므로 직접 바꾸면 안 된다.
            // 새로 생성해서 넣어야 한다. 즉 address 인스턴스를 생성해서 완전히 교체를 해야 한다.
//            Address oldAddress = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("newCity", oldAddress.getStreet(), oldAddress.getZipcode()));

            // 치킨 -> 한식 // 값 타입 컬렉션 set
            // String 자체가 값 타입이므로 업데이트를 할 수 없으므로 제거하고 추가해야 한다.
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("한식");

            // old1 -> newCity1 // 값 타입 컬렉션 list
//            System.out.println("==============");
//            findMember.getAddressHistory().remove(new Address("old1", "street", "10000"));
//            findMember.getAddressHistory().add(new Address("newCity1", "street", "10000"));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}