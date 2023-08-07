package kr.ezen.jpademo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

//@Data // ManyToOneTest시 생략
@Setter @Getter
@Entity
public class Board {
    @Id
//    @GeneratedValue // 테스트용으로 처음 한번 사용
    private Long bno;

    private String title;

    // 다대다 테스트시 주석처리함
//    private String writer;

    @ManyToOne // 여러 개의 게시글에 하나의 User
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String content;

    private Long viewCnt;

    @Temporal(value= TemporalType.TIMESTAMP)
    private Date inDate;
    @Temporal(value= TemporalType.TIMESTAMP)
    private Date upDate;

    @Override
    public String toString() {
        return "Board{" +
                "bno=" + bno +
                ", title='" + title + '\'' +
//                ", user=" + user +
                ", content='" + content + '\'' +
                ", viewCnt=" + viewCnt +
                ", inDate=" + inDate +
                ", upDate=" + upDate +
                '}';
    }
}
