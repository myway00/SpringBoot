package jsbdy.jpa.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Enumeration;

@Component
public class HeaderLoggingInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(HeaderLoggingInterceptor.class);

    @Override
    // 이 부분에선 session 관리 등의 기능 수행
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //위 핸들러는 여기 도달하기 위한 핸들러
        //어떤 함수에 매핑수행할 건지 정해주기 위함
        HandlerMethod handlerMethod = (HandlerMethod) handler;//핸들러형변환
        logger.info("start processing of {}", ((HandlerMethod) handler).getMethod().getName());
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            logger.trace("{}: {}", headerName, request.getHeader(headerName));
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Collection<String> headerNames = response.getHeaderNames();
        for (String headerName : headerNames){
            logger.trace("{} : {} ", headerName, response.getHeader(headerName));
        }
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        logger.info("start processing of {}", handlerMethod.getMethod().getName());
        if (ex != null) logger.error("Exception occurred while processing", ex);
    }
    //Interceptor 다 만든 후엔 Configuration에 등록시켜줘야 함
}
