package jsbdy.jpa;

import jsbdy.jpa.entity.PostEntity;
import jsbdy.jpa.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.nio.channels.ReadPendingException;
import java.util.Iterator;
import java.util.Optional;

@Repository
public class PostDao {
    private static final Logger logger = LoggerFactory.getLogger(PostDao.class);
    private final PostRepository postrepository;
    //postrepository 지정해주기
    public PostDao(
            @Autowired PostRepository postrepository
    ){
        this.postrepository=postrepository;
    }
    public void createPost(PostDto postdto){
        PostEntity postentity = new PostEntity();
        postentity.setTitle(postdto.getTitle());
        postentity.setContent(postdto.getContent());
        postentity.setWriter(postdto.getWriter());
        postentity.setBoardentity(null);
        this.postrepository.save(postentity);
    }

    public PostEntity readPost(int id){
        Optional<PostEntity> postentity = this.postrepository.findById((long) id);
        if(postentity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return postentity.get();

    }

    public Iterator<PostEntity> readPostAll(){
        return this.postrepository.findAll().iterator();
    }


    public void updatePost(int id, PostDto postdto){
        Optional<PostEntity> targetentity = this.postrepository.findById(Long.valueOf(id);
        if (targetentity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        PostEntity postentity=targetentity.get();

        postentity.setTitle(postdto.getTitle()==null?postentity.getTitle() : postdto.getTitle());
        postentity.setContent(postdto.getContent()==null?postentity.getContent() : postdto.getContent());
        postentity.setWriter(postdto.getWriter()==null?postentity.getWriter() : postdto.getWriter());
        postentity.setBoardentity(null);
        this.postrepository.save(postentity);
    }
    public void deletePost(int id){
        Optional<PostEntity> targetentity = this.postrepository.findById((long) id);
        if(targetentity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        this.postrepository.delete(targetentity.get());
    }

}
