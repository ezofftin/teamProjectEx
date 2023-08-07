package kr.ezen.jpademo;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ManyToOneTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

//    @Transactional
    @Test
    public void manyToOneTest() {
        // 1. 테스트 데이터 작성
        User user = new User();
        user.setId("aaa");
        user.setPassword("1234");
        user.setName("LEE");
        user.setEmail("aaa@aaa.com");
        user.setInDate(new Date());
        user.setUpDate(new Date());
        userRepository.save(user);

        Board b1 = new Board();
        b1.setBno(1L);
        b1.setTitle("title1");
        b1.setContent("content1");
        b1.setUser(user);
        b1.setViewCnt(0L);
        b1.setInDate(new Date());
        b1.setUpDate(new Date());
        boardRepository.save(b1);

        Board b2 = new Board();
        b2.setBno(2L);
        b2.setTitle("title2");
        b2.setContent("content2");
        b2.setUser(user);
        b2.setViewCnt(0L);
        b2.setInDate(new Date());
        b2.setUpDate(new Date());
        boardRepository.save(b2);

        b1 = boardRepository.findById(1L).orElse(null);
        b2 = boardRepository.findById(2L).orElse(null);

        System.out.println("b1 = " + b1);
        System.out.println("b2 = " + b2);

        assertTrue(b1!=null);
        assertTrue(b2!=null);

        // FetchType.EAGER
//        user = userRepository.findById(user.getId()).orElse(null);
//        System.out.println("user = " + user);
//        assertTrue(user != null);

        // FetchType.LAZY
        // LAZY라서 user 정보만 가져옴
        user = userRepository.findById(user.getId()).orElse(null);
        // 따로 getList()를 호출해서 board정보를 가져옴
        System.out.println("user = " + user);
        System.out.println("user.getList() = " + user.getList());
        assertTrue(user != null);
    }
}

