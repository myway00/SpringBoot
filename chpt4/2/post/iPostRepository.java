package dev.dongyun.crud.post;

import java.util.List;

public interface iPostRepository {
    boolean save(PostDto dto);
    PostDto findById(int id); //id를 주게 되면 PostDto가 돌아가게 된다
    List<PostDto> findAll();
    boolean update(int id, PostDto dto);
    boolean delete(int id);
}
