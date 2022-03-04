package jsbdy.jpa.repository;
import jsbdy.jpa.entity.BoardEntity;
import jsbdy.jpa.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<PostEntity, Long> {
    List<PostEntity> findAllByWriter(String writer);//where writer = ~~
    /* find하는 기준 isnert,delete, update는 그 entity 자체를 돌려주면 되니 find */
    /* 기본적으로 findby~로 찾게되는 것 */
    List <PostEntity> findAllByWriterAndBoardentity(String writer, BoardEntity entity);
    List <PostEntity> findAllByWriterContaining(String writer);
    //또한 이런 식으로 정의 내릴 수도 있고 아래와 같이 conatins라는 아이같은 것을 활용하기도 가능이다
}
