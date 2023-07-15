package kr.ezen.jpademo;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/*
* 기존에는 CRUD 작업을 할 때
* 엔티티 매니저나 팩토리를 이용해서 처리를 했다.
* 엔티티 매니저를 이용하면 try catch 및 트랜잭션 처리를 해준다.
*
* Spring Data JPA의 장점
* Spring Data JPA를 이용하면 앤티티매니저 사용없이
* 간결하게 CRUD 코드를 작성할 수 있다. 트랜잭션 코드 및 try~catch도 필요없음.
*
* */

@SpringBootTest

// 메소드의 순서를 정하기 위해 사용
// 메소드 앞에 Order()와 함께 사용함
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepo;

    // 삭제 테스트
    @Test
    public void deleteTest() {
        boardRepo.deleteById(1L);

        // 못찾으면 null 반환
        Board board = boardRepo.findById(1L).orElse(null);
        assertTrue(board == null);

    }

    // 수정 테스트
    @Test
    @Order(3)
    public void updateTest() {
        Board board = boardRepo.findById(1L).orElse(null);
        assertTrue(board != null);

        board.setTitle("modified Title");
        boardRepo.save(board); // 수정내용이 save 됨

        // 없으면 새로운 board 객체 반환
        Board board2 = boardRepo.findById(1L).orElse(new Board());
        System.out.println("board = " + board);
        System.out.println("board2 = " + board2);

        assertTrue(board.getTitle().equals(board2.getTitle()));
    }


    // 조회 테스트
    @Test
    @Order(2)
    public void selectTest() {

        // 값이 없을 때는 예외발생
//        Board board = boardRepo.findById(1L).get();
        // 값이 없으면 null을 반환하여 예외를 없앰
        Board board = boardRepo.findById(1L).orElse(null);
        System.out.println("board = " + board);
        assertTrue(board != null);
    }


    // 저장 테스트
    @Test
    @Order(1)
    public void insertTest() {

        Board board = new Board();
        board.setBno(1L);
        board.setTitle("Test ~~~");
        board.setContent("Test contents");
        board.setWriter("tttt");
        board.setViewCnt(0L);
        board.setInDate(new Date());
        board.setUpDate(new Date());

        boardRepo.save(board);
    }
}