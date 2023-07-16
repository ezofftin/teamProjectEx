package kr.ezen.jpademo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long> {

    ////////// 쿼리 메서드 사용예 //////////////
    // : 은 JPQL에서 자동으로 추가
    // SELECT count(*) FROM board WHERE writer = :writer
//    int countAllByWriter(String writer);
    int countAllByWriter(String writer);

    // SELECT * FROM board WHERE writer = :writer
    List<Board> findByWriter(String writer);

    // SELECT * FROM board WHERE title = :title AND writer = :writer
    List<Board> findByTitleAndWriter(String title, String writer);

    @Transactional
        // DELETE FROM board WHERE writer = :writer
    int deleteByWriter(String writer);

    ///////////////////  JPQL 작성 예
    @Query("SELECT b FROM Board b")
    List<Board> findAll();

    @Query("SELECT b FROM Board b WHERE b.title=?1 AND b.writer=?2")
    List<Board> findByTitleAndWriter2(String title, String writer);

    @Query("SELECT b FROM Board b WHERE b.title= :title AND b.writer= :writer")
    List<Board> findByTitleAndWriter3(String title, String writer);


    /////////////////// 네이티브 쿼리 작성 예
    @Query(value = "SELECT * FROM Board", nativeQuery = true)
    List<Board> findAllBySQL();

    @Query(value = "SELECT title, writer FROM Board", nativeQuery = true)
    List<Object[]> findAllBySQL2();
}
