package jsbdy.jpa.test;

import com.fasterxml.jackson.databind.ser.Serializers;
import jsbdy.jpa.exception.BaseException;
import jsbdy.jpa.exception.ErrorResponseDto;
import jsbdy.jpa.exception.InconsistentDataException;
import jsbdy.jpa.exception.PostNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("except")
public class ExceptTestController {
    @GetMapping("{id}")
    public void throwException(@PathVariable int id){
        switch(id){
            case 1:
                throw new PostNotExistException();
            case 2:
                throw new InconsistentDataException();
            default :
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler(BaseException.class)
    //이 함수는 지정된 예외에 대해서 그 예외를 처리 ㅇ
    // 컨트롤러 내부에서 발생하는 모든 baseexception(+얘 상속받은)
    // 예외들이 나타나면 이 메소드 실행
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleBaseException(BaseException exception){
        //이 안에 넣어줄 예외 아이 -> ErrorResponseDto
        return new ErrorResponseDto(exception.getMessage());
    }
}
