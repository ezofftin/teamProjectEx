package kr.ezen.jpademo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
//@Data
@Setter @Getter
public class Member {
    @Id
    @Column(name = "member_id")
    private Long id;
    private String password;
    private String name;
    private String email;

    // 양방향 테스트
//    @OneToOne
//    // 이경우 FK를 만든다.
//    // 데이터베이스에서 member 테이블 확인해보면
//    // cart_id 컬럼이 만들어짐.. FK이기 때문에 필드가 하나 더 만들어짐
//    // 관계형 데이터베이스에서는 FK가 하나만 있으면되는데
//    // 두개가 만들어지기 때문에 OneToOne 테스트시 cart 값이 null이 나옴
//    @JoinColumn(name = "cart_id")

    // mappedBy ="member" : Cart에 의해 member는 매핑되었음을 의미
    @OneToOne(mappedBy = "member") // FK가 안 만들어짐
//    @JoinColumn(name = "cart_id") // 필요없음
    private Cart cart;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cart=" + cart +
                '}';
    }
}
