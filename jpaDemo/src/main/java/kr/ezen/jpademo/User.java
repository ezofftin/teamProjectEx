package kr.ezen.jpademo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class User {
    @Id
    private String id;
    private String password;
    private String name;
    private String email;
    private Date inDate; // 입력일
    private Date upDate; // 수정일
}
