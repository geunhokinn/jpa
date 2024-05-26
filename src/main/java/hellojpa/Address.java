package hellojpa;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String city; // 주소 도시
    private String street; // 주소 번지
//    @Column(name = "ZIPCODE") // 엔티티 처럼 테이블에 이름을 명시할 수도 있다.
    private String zipcode; // 주소 우편번호

//    private Member member; // 임베디드 타입이 엔티티도 가질 수 있다.

    public Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
