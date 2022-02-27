package jsbdy.jpa.repository;

import jsbdy.jpa.entity.BoardEntity;
import org.springframework.data.repository.CrudRepository;

/*
T : 이 레포지토리가 어떤 엔티티 위한 것인지
ID : 아이디가 어떤 타입으로 작성이 되는지
*/

public interface BoardRepository extends CrudRepository<BoardEntity,Long> {
}
