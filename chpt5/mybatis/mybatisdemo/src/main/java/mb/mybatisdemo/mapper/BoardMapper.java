package mb.mybatisdemo.mapper;

import mb.mybatisdemo.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface BoardMapper {
    int createBoard(BoardDto dto);
}
