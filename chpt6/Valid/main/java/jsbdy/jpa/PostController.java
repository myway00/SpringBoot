package jsbdy.jpa;
import com.google.gson.Gson;
import jsbdy.jpa.aspect.LogExecutionTime;
import jsbdy.jpa.aspect.LogArguments;
import jsbdy.jpa.aspect.LogReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postservice;

    public PostController(
            @Autowired PostService postservice,
           @Autowired Gson gson
)      {this.postservice = postservice;
    }

    @LogArguments
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(
            @Valid
            @RequestBody PostDto postdto
    ){
        this.postservice.createPost(postdto);
    }

    @LogReturn
    @GetMapping("{id}")
    public PostDto readPost(
            @PathVariable("id")int id
    ){
       return this.postservice.readPost(id);
    }

    @LogExecutionTime
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


    @GetMapping("test-log")
    public void testLog(){
        logger.trace("Trace Log");
        logger.debug("debug log");
        logger.info("info log");
        logger.warn("warn log");
        logger.error("error log");
    }

    @PostMapping("test-valid")
    public void testValid(@Valid @RequestBody ValidTestDto dto){
        logger.info(dto.toString());
    }
}
