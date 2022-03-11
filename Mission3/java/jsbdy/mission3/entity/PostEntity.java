package jsbdy.mission3.entity;

import org.apache.catalina.User;

import javax.persistence.*;

//private Long id;
//private String title;
//private String content;
//private String writer;
//private String password;
//private Long boardId;
@Entity
@Table(name="post")
public class PostEntity {
    public PostEntity() {
    }
    @Id /*jpa에게 아래의 아이기 pk라는 것임을 알려주는 것*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*설정->테이블을 생성하며 autogenerated,increment,pk같은거지정시*/
    /*아이디 만들때 규칙 명시한 것*/
    private Long id;
    /*jpa 사용하면 프리미티브 타입 말고 클래스 기반 오브젝트 사용*/
    private String title;
    private String content;
    private String writer;
    private String password;
    @ManyToOne(
            /*어떠한 관계를 대상으로 관계맺나?*/
            targetEntity = BoardEntity.class,
            fetch = FetchType.LAZY
    ) /*다대일관계*/
    @JoinColumn(name="board_id")
    private BoardEntity boardentity;

    @ManyToOne(
            targetEntity = UserEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name="user_id")
    private UserEntity userentity;

    public PostEntity(Long id, String title, String content, String writer, String password, BoardEntity boardentity, UserEntity userentity) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
        this.boardentity = boardentity;
        this.userentity = userentity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BoardEntity getBoardentity() {
        return boardentity;
    }

    public void setBoardentity(BoardEntity boardentity) {
        this.boardentity = boardentity;
    }

    public UserEntity getUserentity() {
        return userentity;
    }

    public void setUserentity(UserEntity userentity) {
        this.userentity = userentity;
    }

    @Override
    public String toString() {
        return "PostEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", password='" + password + '\'' +
                ", boardentity=" + boardentity +
                ", userentity=" + userentity +
                '}';
    }
}