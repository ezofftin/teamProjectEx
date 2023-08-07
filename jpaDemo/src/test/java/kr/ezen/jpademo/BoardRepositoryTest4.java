//package kr.ezen.jpademo;
//
//import com.querydsl.core.BooleanBuilder;
//import com.querydsl.core.Tuple;
//import com.querydsl.jpa.impl.JPAQuery;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.persistence.EntityManager;
//
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class BoardRepositoryTest4 {
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
//            board.setContent("content" + i);
//            board.setWriter("writer" + (i % 5)); // writer0 ~ 4
//            board.setViewCnt((long) (Math.random() * 100)); // 0 ~ 99
//            board.setInDate(new Date());
//            board.setUpDate(new Date());
//            boardRepository.save(board);
//        }
//    }
//
//    QBoard board = QBoard.board;
//    @Test
//    @DisplayName("querydsl 작성 테스트")
//    public void querydslTest() {
//
//        // 1. JPAQueryFactory() 생성
//        JPAQueryFactory jqf = new JPAQueryFactory(em);
//
//        // 2. 쿼리 작성
//        JPAQuery<Board> query = jqf.selectFrom(board)
//                .where(board.title.eq("title1"));
//
//        // 3. 쿼리 실행
//        List<Board> list = query.fetch();
//        list.forEach(obj -> System.out.println(obj));
////        list.forEach(System.out::println);
//    }
//
//
//    @Test
//    @DisplayName("querydsl 작성 테스트 -- 복잡한 쿼리 작성")
//    public void querydslTest2() {
////        QBoard board = QBoard.board;
//
//        JPAQueryFactory jqf = new JPAQueryFactory(em);
//
//        JPAQuery<Tuple> query = jqf.select(board.writer, board.viewCnt.sum()).from(board)
//                .where(board.title.notLike("title1%"))
//                .where(board.writer.eq("writer1"))
//                .where(board.content.contains("content"))
//                .where(board.content.isNotNull())
//                .groupBy(board.writer)
//                .having(board.viewCnt.sum().gt(50))
//                .orderBy(board.writer.asc())
//                .orderBy(board.viewCnt.sum().desc());
//
//        List<Tuple> list = query.fetch();
//        list.forEach(System.out::println);
//    }
//
//    @Test
//    @DisplayName("querydsl 작성 테스트 -- 동적 쿼리 작성")
//    public void querydslTest3() {
//        String searchType = "T"; // 제목(title)과 작성내용(content)에서 검색
//        String keyword = "7";
//        keyword = "%" + keyword + "%";
//
//        BooleanBuilder builder = new BooleanBuilder();
//
//        // 조건에 따른 동적 쿼리
//        if (searchType.equalsIgnoreCase("T"))
//            builder.and(board.title.like(keyword));
//        else if (searchType.equalsIgnoreCase("C"))
//            builder.and(board.content.like(keyword));
//        else if (searchType.equalsIgnoreCase("TC"))
//            builder.and(board.title.like(keyword).or(board.content.like(keyword)));
//
//        JPAQueryFactory jqf = new JPAQueryFactory(em);
//        JPAQuery query = jqf.selectFrom(board)
//                .where(builder)
//                .orderBy(board.upDate.desc());
//
//        List<Board> list = query.fetch();
//        list.forEach(System.out::println);
//    }
//}