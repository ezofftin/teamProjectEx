package kr.ezen.jpademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@SpringBootApplication

//@SpringBootConfiguration // @Configuration(자바클래스를 이용한 설정)
//@EnableAutoConfiguration // 우리가 사용하는 라이브러리를 자동으로 등록해줌
//@ComponentScan
public class JpaDemoApplication implements CommandLineRunner {
//public class JpaDemoApplication {

    @Autowired
    EntityManagerFactory emf; // 인스턴스 변수

    public static void main(String[] args) {
//        SpringApplication.run(JpaDemoApplication.class, args);
        // 인스턴스변수라서 static안에서 주입받아 사용할 수 없음.
        // 이럴 경우 CommandLineRunner 인터페이스를 구현하여
        // run 인스턴스 메서드를 오버라이딩해서 사용하면 됨
//        System.out.println("emf = " + emf);

        // 톰캣실행을 하지 않기
        SpringApplication app = new SpringApplication(JpaDemoApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE); // <-- 톰캣이 동작 안함
        app.run(args);
    }

    // EntityManagerFactory emf 객체를 주입받아서 사용하려면
    // CommandLineRunner 인터페이스를 구현해서
    // run() 이라는 인스턴스 메소드를 이용하여 static main() 메소드
    // 대신 사용하면 된다.

    @Override // 인스턴스 메서드, 따라서 emf 인스턴스 변수를 사용할 수 있음
    public void run(String... args) throws Exception {
        System.out.println("emf = " + emf);

        EntityManager em = emf.createEntityManager();
//        EntityManager em2 = emf.createEntityManager();

        System.out.println("em = " + em);
//        System.out.println("em2 = " + em2);

        // 출력내용 : 서로 다른 객체 임을 확인
        //        em = SessionImpl(1736957266<open>)
        //        em2 = SessionImpl(1863103374<open>)


        // EntityManager 실습
        User user = new User();
        user.setId("aaa");
        user.setPassword("1234");
        user.setName("Kim");
        user.setEmail("aaa@gmail.com");
        user.setInDate(new Date());
        user.setUpDate(new Date());

        EntityTransaction tx = em.getTransaction();

        // 1. 저장
        tx.begin(); // 트랜잭션 시작
        em.persist(user); // user엔티티를 em에 영속화(저장)
        // 똑같은 아이디로 같은 엔티티를 저장해도 저장안됨
        // 실수를 해도 중복에러가 나지 않음, Entity Manager가 알아서 저장안함

        // 2. 수정
        // PersistenceContext가 변경을 감지하고 update를 수행함
        user.setPassword("2222");
        user.setEmail("tttt@gmail.com");
        em.persist(user);
        tx.commit(); // 트랜잭션 종료(DB에 반영)

        // 3. 조회
        // 출력결과에 select 문이 안보임, 이유는
        // 엔티티 매니저가 해당 user를 관리하기 때문에
        // DB까지 가지 않음, 즉, 효율적으로 관리하고 있음.
        User user2 = em.find(User.class, "aaa");
        System.out.println("user==user2 = " + (user == user2));

        // 출력결과에 select문이 보임, 이유는
        // bbb가 엔티티 매니저에 없기 때문에 DB에서 가져옴.
        User user3 = em.find(User.class, "bbb");
        System.out.println("user3 = " + user3); // db에 없음 null

        // 4. 삭제
        tx.begin();
        em.remove(user); // em에서 user엔티티 삭제(em에 반영)
        tx.commit(); // db에 반영

    }



    }
