package mb.mybatisdemo.mapper;

import mb.mybatisdemo.dto.PostDto;
import java.util.List;

public interface PostMapper{
    int createPostAll(List<PostDto> dtoList);
    int createPost(PostDto dto);
    PostDto readPost(int id);
    List<PostDto> readPostAll();
    PostDto readPostQuery(PostDto dto);//추가
    int updatePost(PostDto dto);
    int deletePost(int id);
}
