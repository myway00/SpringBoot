package jsbdy.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
//테스트하면서 jpa를 활용할텐데 원래는 이 어노테이션 JpaApplication클래스에 있삼
//근데 그렇게 되면 어플리케이션 전체 굴림, 근데 jpa를 다 체크하면서 짜기엔 너무 과함
//우린 몇개 어플리케이션만 굴릴거라서 여기다가 붙여줄 거임
//그래서 거기서 삭제 후 여기서 jpa 를 활용할 것임
public class JpaAuditConfig {
}
