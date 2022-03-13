package jsbdy.jpa.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class TransactionLogFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(TransactionLogFilter.class);

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        String requestUUID = UUID.randomUUID().toString().split("-")[0];//단순 구분자
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        logger.debug("[{}] start request : {} {}",
                requestUUID,
                httpServletRequest.getMethod(),
                httpServletRequest.getRequestURI()
            );
        //chain 호출하기 전까지가 filter 이 spring에 전달되기 전
        logger.info("* response status code : {}",((HttpServletResponse)response).getStatus());

        chain.doFilter(request, response); //chain 호출

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        logger.info("* response status code : {}",((HttpServletResponse)response).getStatus());
        logger.info("[{}], send response : {} ",
                requestUUID, httpServletResponse.getStatus());
    }
}
