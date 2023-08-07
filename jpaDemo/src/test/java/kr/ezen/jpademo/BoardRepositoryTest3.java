//package kr.ezen.jpademo;
//
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.jpa.repository.Query;
//
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class BoardRepositoryTest3 {
//
//    @Autowired
//    private BoardRepository boardRepository;
//
//    @Autowired
//    private EntityManager em;
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
//    @DisplayName("createQuery JPQL작성 테스트")
//    public void createQueryTest() {
//        String query = "SELECT b FROM Board b";
//        TypedQuery<Board> tQuery = em.createQuery(query, Board.class);
//        List<Board> list = tQuery.getResultList();
//
//        // list.forEach(System.out::println);
//        assertTrue(list.size() == 100);
//    }
//
//    @Test
//    @DisplayName("@Query JPQL작성 테스트")
//    public void queryAnnoTest() {
//        List<Board> list = boardRepository.findAll();
//        assertTrue(list.size() == 100);
//    }
//
//    @Test
//    @DisplayName("@Query JPQL작성 테스트 -- 매개변수 순서")
//    public void queryAnnoTest2() {
////        @Query("SELECT b FROM Board b WHERE b.title=?1 AND b.writer=?2")
//        List<Board> list = boardRepository.findByTitleAndWriter2("title1", "writer1");
//        assertTrue(list.size() == 1);
//    }
//
//    @Test
//    @DisplayName("@Query JPQL작성 테스트 -- 매개변수 이름")
//    public void queryAnnoTest3() {
////        @Query("SELECT b FROM Board b WHERE b.title= :title AND b.writer= :writer")
//        List<Board> list = boardRepository.findByTitleAndWriter3("title1", "writer1");
//        assertTrue(list.size() == 1);
//    }
//
//    @Test
//    @DisplayName("@Query Native 쿼리 테스트")
//    public void queryAnnoTest4() {
////        @Query(value = "SELECT * FROM Board", nativeQuery = true)
////        List<Board> findAllBySQL();
//        List<Board> list = boardRepository.findAllBySQL();
//        assertTrue(list.size() == 100);
//    }
//
//    @Test
//    @DisplayName("@Query Native 쿼리 테스트")
//    public void queryAnnoTest5() {
//
////        @Query(value = "SELECT title, writer FROM Board", nativeQuery = true)
////        List<Object[]> findAllBySQL2();
//        List<Object[]> list = boardRepository.findAllBySQL2();
////        list.forEach(obj -> System.out.println(obj));
////        list.forEach(System.out::println);
//        list.stream()
//                .map(arr -> Arrays.toString(arr))
//                .forEach(System.out::println);
//
//        assertTrue(list.size() == 100);
//    }
//
//
//
//}