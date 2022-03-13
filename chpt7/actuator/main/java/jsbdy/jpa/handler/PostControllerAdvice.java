package jsbdy.jpa.handler;

import com.fasterxml.jackson.databind.ser.Serializers;
import jsbdy.jpa.exception.BaseException;
import jsbdy.jpa.exception.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
//@ControllerAdvice
//@RestControllerAdvice로 어노붙이면 아래서 ResponseBody 없애주기
public class PostControllerAdvice {
    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public @ResponseBody ErrorResponseDto handleException(BaseException exception){
    public ErrorResponseDto handleException(BaseException exception){
        return new ErrorResponseDto(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    //valid는 에러가 다수일 때도 있어서 해당 경우를 예외처리해주기도 필요할 수도
    public ErrorResponseDto handleValidException(
            MethodArgumentNotValidException exception) {
           return new ErrorResponseDto(exception.getMessage());
    }
}
