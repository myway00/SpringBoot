package jsbdy.jpa.entity;

import javax.persistence.*;

/*
* id int
* title varchar
* content varchar
* writer varchar
* board int*/
@Entity
@Table(name="post")
public class PostEntity extends BaseEntity  {
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

    /*boarentity와의 관계 명시를 위해 추가*/
//    @ManyToOne(
//            /*어떠한 관계를 대상으로 관계맺나?*/
//            targetEntity = BoardEntity.class,
//            fetch = FetchType.LAZY
//    ) /*다대일관계*/
//    @JoinColumn(name="board_id")
////    private BoardEntity boardentity;
//    /* 여러개의 post,게시글은 한개의 board,게시판에 소속돼있는 것*/

    public PostEntity(Long id, String title, String content, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
//        this.boardentity = boardentity;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getWriter() {
        return writer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public void setContent(String content) {
        this.content = content;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "PostEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                '}';
    }
}
