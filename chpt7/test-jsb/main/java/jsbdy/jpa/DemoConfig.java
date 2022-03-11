package jsbdy.jpa;

//import com.google.gson.Gson;
import jsbdy.jpa.interceptor.HeaderLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//import javax.annotation.PostConstruct;
//import java.util.List;

@Configuration
//WebMvcConfigurer 을 implement 수행
//addInterceptors 를 상속받기 위해서 상속받는 것
public class DemoConfig implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(DemoConfig.class);

    private final HeaderLoggingInterceptor headerLoggingInterceptor;

    public DemoConfig(
            @Autowired HeaderLoggingInterceptor headerLoggingInterceptor
    ){
        this.headerLoggingInterceptor=headerLoggingInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(headerLoggingInterceptor)
                .addPathPatterns("post/**")
                .excludePathPatterns("/except/**");
    }
}
