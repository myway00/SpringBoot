package mb.mybatisdemo.dto;
/*
id int
title varchar
content varchar
writer varchar
board varchar
*/
public class PostDto {
    private int id;
    private String title;
    private String content;
    private String writer;
    private int board;

    public PostDto() {
    }

    public PostDto(int id, String title, String content, String writer, int board) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.board = board;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public int getBoard() {
        return board;
    }

    public void setId(int id) {
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

    public void setBoard(int board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", board='" + board + '\'' +
                '}';
    }
}
