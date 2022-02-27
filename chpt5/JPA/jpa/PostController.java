package jsbdy.jpa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postservice;

    public PostController(
            @Autowired PostService postservice
    ){
        this.postservice = postservice;
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(
            @RequestBody PostDto postdto
    ){
        this.postservice.createPost(postdto);
    }

    @GetMapping("{id}")
    public PostDto readPost(
            @PathVariable("id")int id
    ){
       return this.postservice.readPost(id);
    }

    @GetMapping("")
    public List<PostDto > readPostAll(){
        return this.postservice.readPostAll();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePost(
            @PathVariable("id")int id,
            @RequestBody PostDto postdto
    ){
        this.postservice.updatePost(id, postdto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePost(
            @PathVariable("id") int id
    ){
        this.postservice.deletePost(id);
    }
}
