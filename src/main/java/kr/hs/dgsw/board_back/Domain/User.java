package kr.hs.dgsw.board_back.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    // 이름
    @Column(nullable = false)
    private String name;
    // 아이디
    @Column(nullable = false)
    private String account;
    // 비밀번호
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // 성별 남자 0, 여자 1
    @Column(nullable = false)
    private Long gender;
    // 나이
    @Column(nullable = false)
    private Long age;
    // 이미지
    private Long attachmentId;

    // 자신의 글
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "userId")
    private List<Board> boardList;

    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime updated;


    // 테스트 게시글 작성 용으로 만드는 테스트 유저 생성자
    public User(String name, String account, String password, Long gender, Long age) {
        this.name = name;
        this.account = account;
        this.password = password;
        this.gender = gender;
        this.age = age;
    }
}
