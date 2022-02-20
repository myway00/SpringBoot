package mb.mybatisdemo.dao;

import mb.mybatisdemo.dto.PostDto;
import mb.mybatisdemo.mapper.PostMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDao {
    private final SqlSessionFactory sessionFactory;

    public PostDao(
            @Autowired SqlSessionFactory sessionFactory
    ){
        this.sessionFactory=sessionFactory;
    }

    public int createPost(PostDto dto){
        try (SqlSession session = sessionFactory.openSession()){
            PostMapper mapper=session.getMapper(PostMapper.class);
            return mapper.createPost(dto);
        }
    }

    public PostDto readPost(int id){
        try (SqlSession session = sessionFactory.openSession()){
            PostMapper mapper=session.getMapper(PostMapper.class);
            //왜 굳이 sessionFactory에서 세션을 열고 닫지?
            //걍 매퍼만 바로 사용하면 안됨?이라는 의문들기 가능
            //mapperisntance는 threadsafe하지 않음
            return mapper.readPost(id);
        }
    }

    public List<PostDto> readPostAll(){
        try (SqlSession session = sessionFactory.openSession()){
            PostMapper mapper=session.getMapper(PostMapper.class);
            return mapper.readPostAll();
        }
    }

    public int updatePost(PostDto dto) {
        try (SqlSession session = sessionFactory.openSession()) {
            PostMapper mapper = session.getMapper(PostMapper.class);
            return mapper.updatePost(dto);
        }
    }
    public int deletePost(int id) {
        try (SqlSession session = sessionFactory.openSession()) {
            PostMapper mapper = session.getMapper(PostMapper.class);
            return mapper.deletePost(id);
        }
    }
}
