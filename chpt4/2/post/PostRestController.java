package dev.dongyun.crud.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("post")
public class PostRestController {
    private static final Logger logger = LoggerFactory.getLogger(PostRestController.class);
    private final PostService postservice;
    public PostRestController(
            @Autowired PostService postservice
    ){
        this.postservice = postservice;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostDto postDto, HttpServletRequest request){
        logger.info("content");
        logger.info(postDto.toString());
        logger.info("full request");
        logger.info(String.valueOf(request));
        logger.info("method of request");
        logger.info(request.getMethod());
        this.postservice.createPost(postDto);
    }

    @GetMapping()
    public List<PostDto> readPostAll(){
        logger.info("read-all");
        return this.postservice.readPostAll();
    }

    //GET
    //GET /post?id=0

    @GetMapping("{id}}")
    public PostDto readPost(@PathVariable("id") int id){
        logger.info("in read post");
        return this.postservice.readPost(id);
    }

    @PutMapping("{id}")
    public void updatePost(
            @PathVariable("id") int id,
            @RequestBody  PostDto postDto
    ){
        PostDto targetPost = this.postservice.readPost(id); //업데이트를 위한 목적 타겟
        if (postDto.getTitle()!=null){
            targetPost.setTitle(postDto.getTitle());
        }
        if (postDto.getContent()!=null){
            targetPost.setContent(postDto.getContent());
        }
        this.postservice.updatePost(id, targetPost);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable("{id}") int id){
        this.postservice.deletePost(id);
    }
}
