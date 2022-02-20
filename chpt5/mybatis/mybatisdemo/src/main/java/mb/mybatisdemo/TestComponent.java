package mb.mybatisdemo;

import mb.mybatisdemo.dao.BoardDao;
import mb.mybatisdemo.dao.PostDao;
import mb.mybatisdemo.dto.BoardDto;
import mb.mybatisdemo.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestComponent {
    private final PostDao postdao;
    //private final BoardDao boardDao;

    public TestComponent(
            @Autowired PostDao postdao, BoardDao boardDao) {
        this.postdao = postdao;
        //this.boardDao = boardDao;
//
//        BoardDto boarddto = new BoardDto();
//        boarddto.setName("new board");
//        this.boardDao.createBoard(boarddto);
//        System.out.println(boarddto.getId());

        PostDto newPost = new PostDto();
        newPost.setTitle("From mybatis");
        newPost.setContent("Hello batis");
        newPost.setWriter("shucream");
        newPost.setBoard(0);
        this.postdao.createPost(newPost);

        List<PostDto> postDtoList=this.postdao.readPostAll();
        System.out.println(postDtoList.size()-1);

        PostDto firstPost = postDtoList.get(0);
        firstPost.setContent("content updated by batis");
        postdao.updatePost(firstPost);

        System.out.println(this.postdao.readPost(firstPost.getId()));
    }
}