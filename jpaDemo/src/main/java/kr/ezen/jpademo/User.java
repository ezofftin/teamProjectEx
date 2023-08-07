package kr.ezen.jpademo;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @Column(name = "user_id")
    private String id;

    // FetchType.EAGER : 두 엔티티의 정보를 한번에 가져오기(join)
    // FetchType.LAZY : 필요시 따로 가져올 때 설정,  getList()

    // user하나에 여러 게시글
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Board> list = new ArrayList<>();

    private String password;
    private String name;
    private String email;
    private Date inDate; // 입력일
    private Date upDate; // 수정일
}

