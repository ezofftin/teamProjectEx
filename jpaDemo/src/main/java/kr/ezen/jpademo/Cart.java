package kr.ezen.jpademo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
//@Data
@Setter @Getter // 양방향 테스트시 @Data --> @Setter @Getter 바꿔줌
public class Cart {
    @Id
    // 양방향 테스트시 @Column 추가
    @Column(name = "cart_id")
    public Long id;

    @OneToOne
    // nullable =false는 필수 --> inner join이 된다.
    // nullable =false가 없으면 outer join으로 처리된다.
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                // 주석처리를 안하면 stackOverflow발생
                // Member 클래스의 toString()안에
                // cart는 cart.toString()이다
                // 따라서, 여기 toString()이 호출되고
                // 이곳에 있는 member도 member.toString()
                // 즉, 계속 서로의 toString()을 호출하는 문제가
                // 발생한다. 따라서, 한곳은 없애야 한다.
//                ", member=" + member +
                '}';
    }
}
