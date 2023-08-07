//package kr.ezen.jpademo;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//
//import javax.persistence.EntityManagerFactory;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class BoardRepositoryTest2 {
//
//    @Autowired
//    private BoardRepository boardRepository;
//
//    @BeforeEach
//    public void dummyData() {
//        for (int i = 1; i <= 100; i++) {
//            Board board = new Board();
//            board.setBno((long) i);
//            board.setTitle("title" + i);
//            board.setContent("Content" + i);
//            board.setWriter("writer" + (i % 5));
//            board.setViewCnt((long) Math.random() * 100);
//            board.setInDate(new Date());
//            board.setUpDate(new Date());
//            boardRepository.save(board);
//        }
//    }
//
//    @Test
//    public void countTest() {
//        assertTrue(boardRepository.countAllByWriter("writer1") == 20);
//    }
//
//    @Test
//    public void findTest() {
//        List<Board> list = boardRepository.findByWriter("writer2");
//        assertTrue(list.size() == 20);
////        list.forEach(System.out::println);
//    }
//
//    @Test
//    public void deleteTest() {
//        // 삭제행의 개수를 리턴한다.
//        assertTrue(boardRepository.deleteByWriter("writer2") == 20);
//        List<Board> list = boardRepository.findByWriter("writer2");
//        assertTrue(list.size() == 0);
//    }
//
//    @Test
//    public void titleAndWriter() {
//        List<Board> list = boardRepository.findByTitleAndWriter("title1", "writer1");
//        assertTrue(list.size() == 1);
//    }
//
//}