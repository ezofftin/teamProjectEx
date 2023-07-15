package kr.ezen.jpademo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Board {
    @Id
//    @GeneratedValue // 테스트용으로 처음 한번 사용
    private Long bno;

    private String title;
    private String writer;
    private String content;
    private Long viewCnt;

    @Temporal(value= TemporalType.TIMESTAMP)
    private Date inDate;
    @Temporal(value= TemporalType.TIMESTAMP)
    private Date upDate;
}
