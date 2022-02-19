package dev.dongyun.crud.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
//Deprecated는 당장 지원은 해주지만 곧 쓰지 못할 버전이라고 알려주는 것
//오로지 응답을 받고 요청을 보내는 역할로만
@Controller
@ResponseBody
//이렇게 붙여놓으면
//클래스 안 모든 함수들이 responsebody가
// 붙은 형태로 함수 선언 완료

//@RequestMapping("post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postservice;

    public PostController(

          @Autowired PostService postservice) {
        this.postservice = postservice;
        //왜 위에는 list고 아래는 arraylist냐
        // list는 인터페이스, arraylist는 구현체
    }
    @PostMapping("create")
    public void createPost(@RequestBody PostDto postDto){
        logger.info(postDto.toString());
        this.postservice.createPost(postDto);
    }

    @GetMapping("read-all")
        public List<PostDto>readPostAll(){
            logger.info("read all");
            return this.postservice.readPostAll();
        }
    @GetMapping("read-one")
    public PostDto readPostOne(@RequestParam("id") int id){
        logger.info("read one");
        return this.postservice.readPost(id);
    }

    @PatchMapping("update")
    public void updatePost(
            @RequestParam("id") int id,
            @RequestBody PostDto postDto //포스트 요청의 body

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

    @DeleteMapping("delete")
    public void deletePost(@RequestParam("id") int id){
        this.postservice.deletePost(id);
    }
}
