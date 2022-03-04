package jsbdy.jpa;

import jsbdy.jpa.entity.PostEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);
    private final PostDao postdao; //postdao 추가 선언 (활용 위해)

    public PostService(@Autowired PostDao postdao, PostDao postdao1){
        this.postdao = postdao; //postdao 초기화
    }

    public void createPost(PostDto postdto){
    this.postdao.createPost(postdto);
    }

    public PostDto readPost(int id){
        PostEntity postentity = this.postdao.readPost(id);
        return new PostDto(
                Math.toIntExact(postentity.getId()),
                postentity.getTitle(),
                postentity.getContent(),
                postentity.getWriter(),
                postentity.getBoardentity()==null
                        ?0:Math.toIntExact(postentity.getBoardentity().getId())
        );
    }

    public List<PostDto> readPostAll(){
        Iterator<PostEntity> iterator=this.postdao.readPostAll();
        List<PostDto> postDtoList = new ArrayList<>();

        while(iterator.hasNext()){
            PostEntity postentity = iterator.next();
            postDtoList.add(new PostDto(
                    Math.toIntExact(postentity.getId()),
                    postentity.getTitle(),
                    postentity.getContent(),
                    postentity.getWriter(),
                    postentity.getBoardentity()==null
                            ?0:Math.toIntExact(postentity.getBoardentity().getId())

            ));
        }
        return postDtoList;
    }

    public void updatePost(int id, PostDto postdto){
        this.postdao.updatePost(id,postdto);
    }

    public void deletePost(int id){
        this.postdao.deletePost(id);
    }
}
