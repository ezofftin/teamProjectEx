package kr.ezen.jpademo;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OneToOneTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void oneToOneTest() {
        Member member = new Member();
        member.setId(1L);
        member.setName("abc");
        member.setEmail("test@gmail.com");
        member.setPassword("1212");

        memberRepository.save(member);

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setMember(member);
        cartRepository.save(cart);

        cart = cartRepository.findById(cart.getId()).orElse(null);
        assertTrue(cart != null);
        System.out.println("cart = " + cart);

        // 양방향 연결시
        member = memberRepository.findById(member.getId()).orElse(null);
        System.out.println("member = " + member);
        assertTrue(member != null);

        // &allowPublicKeyRetrieval=true& 에러 시 application.properties에 DataSource에 추가
    }
}