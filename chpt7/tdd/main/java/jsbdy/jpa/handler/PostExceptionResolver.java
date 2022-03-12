package jsbdy.jpa.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jsbdy.jpa.exception.BaseException;
import jsbdy.jpa.exception.ErrorResponseDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class PostExceptionResolver extends AbstractHandlerExceptionResolver {
    @Override//추상클래스의 메소드 받아오기
    protected ModelAndView doResolveException(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {
        logger.debug(ex.getClass());
        if(ex instanceof BaseException) {
            //만약 예외가 BaseException의 구현체라면 ModelandView를 반환하지 않고
            //데이터만 돌려주고 시픔
            //이를 위한 간단한 처리 방법은 아래와 같음
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try {
                response.getOutputStream().print(
                        //여기에 실제적인 반환문자열 입력해줘야 함
                        new ObjectMapper().writeValueAsString(
                                //자바 객체 -> json 으로 변경해준다
                                new ErrorResponseDto("in resolver, message : " +
                                        ex.getMessage()
                                )
                        )
                );
                response.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
                return new ModelAndView();
            } catch (IOException e) {

                //e.printStackTrace();
                logger.warn("Handling exception caused exception : {}", e);
            }
        }
        return null;//null은 예외가 처리되지 못했을 때 반환되는 값
    }
}
