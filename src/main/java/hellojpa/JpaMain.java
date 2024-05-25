package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team); // insert

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team); // 연관 관계 편의 메서드
            em.persist(member); // insert
            // 여기까지 team 이 그대로 영속성 컨텍스트에 들어가 있음

//            team.addMember(member); // 연관 관계 편의 메서드(반대로)
            // 연관 관계 편의 메서드는 한 쪽만 사용하자 -> 이건 규칙을 정하면 된다

//            team.getMembers().add(member); // setter 에 넣음

//            em.flush();
//            em.clear();
            // 주석을 했기 때문에 team 의 member 컬렉션에 아무 것도 없음
            // 따라서 밑에 member 는 출력되는 것이 없음
            // 그래서 객체 지향적으로 생각했을 때 양쪽에 값을 세팅해야 함
            // 까먹을 수 있으니 연관 관계 편의 메서드를 만들어서 값을 세팅하자

            Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시
            List<Member> members = findTeam.getMembers();

            System.out.println("findTeam = " + findTeam);

//            for (Member m : members) {
//                System.out.println("m.getUsername() = " + m.getUsername());
//            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
