package dev.dongyun.crud.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostServiceimple implements PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostServiceimple.class);

    private final iPostRepository postRepository;

    public PostServiceimple(
           @Autowired iPostRepository postRepository
    ) {
        this.postRepository = postRepository;
    }


    @Override
    public List<PostDto> readPostAll() {
        return this.postRepository.findAll();
    }

    @Override
    public void createPost(PostDto dto) {
        if(!this.postRepository.save(dto)){
            throw new RuntimeException(("save failed"));
        }
        this.postRepository.save(dto);
    }
    @Override
    public PostDto readPost(int id) {
        return this.postRepository.findById(id);
    }

    @Override
    public void updatePost(int id, PostDto dto) {
        this.postRepository.update(id,dto);

    }

    @Override
    public void deletePost(int id) {
        this.postRepository.delete(id);
    }
}
