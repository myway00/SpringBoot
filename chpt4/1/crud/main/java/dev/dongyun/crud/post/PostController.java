package dev.dongyun.crud.post;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
//이렇게 붙여놓으면
//클래스 안 모든 함수들이 responsebody가
// 붙은 형태로 함수 선언 완료

@RequestMapping("post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final List<PostDto> postList;

    public PostController() {
        postList = new ArrayList<>();
        //왜 위에는 list고 아래는 arraylist냐
        // list는 인터페이스, arraylist는 구현체
    }
    @PostMapping("create")
    public void createPost(@RequestBody PostDto postDto){
        logger.info(postDto.toString());
        this.postList.add(postDto);
    }

    @GetMapping("read-all")
        public List<PostDto>readPostAll(){
            logger.info("read all");
            return this.postList;
        }
    @GetMapping("read-one")
    public PostDto readPostOne(@RequestParam("id") int id){
        logger.info("read one");
        return this.postList.get(id);
    }

    @PatchMapping("update")
    public void updatePost(
            @RequestParam("id") int id,
            @RequestBody PostDto postDto //포스트 요청의 body

    ){
        PostDto targetPost = this.postList.get(id); //업데이트를 위한 목적 타겟
        if (postDto.getTitle()!=null){
            targetPost.setTitle(postDto.getTitle());
        }
        if (postDto.getContent()!=null){
            targetPost.setContent(postDto.getContent());
        }
        this.postList.set(id, targetPost);
    }

    @DeleteMapping("delete")
    public void deletePost(@RequestParam("id") int id){
        this.postList.remove(id);
    }
}
