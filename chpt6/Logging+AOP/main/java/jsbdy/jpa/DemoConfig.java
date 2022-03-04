package jsbdy.jpa;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
public class DemoConfig {
    private static final Logger logger = LoggerFactory.getLogger(DemoConfig.class);
    @Value("${custom.property.single}")
    //yml에 설정돼있는 값을 찾아서 얘가 들어가게 됨
    private String customProperty;

    @Value("${custom.property.comlist}")
    private List<String> customCommaList;

    @Value("${custom.property.default:default-property}")
    //저 default 모시기는 우리가 yml에서 정의안해둔 것
    //그래서 콜론 뒤에 쟤가 호출되면 불러와질 것 우리가 써줌(default-property를 디폴트로)
    private String propertyDefault;

    @PostConstruct
    public void init() {
        logger.info("custom property:{}",customProperty);
        for(String commaListItem:customCommaList){
            logger.info("Comma list item:{}",commaListItem);
        }

        logger.info("default property:{}", propertyDefault);
    }

    @Bean
    public Gson gson(){
        return new Gson();
    }
}
