package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//public class Member extends BaseEntity {
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    // 기간 Period
//    @Embedded
//    private Period workPeriod;

    // 집 주소 주소(값 타입)
    @Embedded
    private Address homeAddress;

    @ElementCollection // 값 타입 컬렉션
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns =
        @JoinColumn(name = "MEMBER_ID")
    )
    @Column(name = "FOOD_NAME") // 내가 정의한 것이 아니고 컬럼명이 하나이다. 예외적으로 허용해준다.
    private Set<String> favoriteFoods = new HashSet<>();

//    @ElementCollection // 값 타입 컬렉션
//    @CollectionTable(name = "ADDRESS", joinColumns =
//        @JoinColumn(name = "MEMBER_ID")
//    )
//    private List<Address> addressHistory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
    }

    // 회사 주소 주소
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name="city", column = @Column(name = "WORK_CITY")),
//            @AttributeOverride(name="street", column = @Column(name = "WORK_STREET")),
//            @AttributeOverride(name="zipcode", column = @Column(name = "WORK_ZIPCODE"))
//    })
//    private Address workAddress;

//    public Period getWorkPeriod() {
//        return workPeriod;
//    }
//
//    public void setWorkPeriod(Period workPeriod) {
//        this.workPeriod = workPeriod;
//    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    //    @ManyToOne(fetch = FetchType.LAZY) // team 을 프록시 객체로 조회함
//    @JoinColumn(name = "TEAD_ID")
//    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false) // 읽기 전용
//    private Team team;

//    @OneToOne
//    @JoinColumn(name = "LOCKER_ID")
//    private Locker locker;

//    @OneToMany(mappedBy = "member")
//    private List<MemberProduct> memberProducts = new ArrayList<>();
//
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
//
//    public Team getTeam() {
//        return team;
//    }
//
//    public void setTeam(Team team) {
//        this.team = team;
//    }
}
