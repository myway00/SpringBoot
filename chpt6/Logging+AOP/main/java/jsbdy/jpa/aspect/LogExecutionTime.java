package jsbdy.jpa.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
//타켓 어노 : 아래 아이가 어디에 붙을 수 있는지 지정해주는 것
//즉 얘는 함수에 부티 위한 아이야 라고 알려주는 것
@Retention(RetentionPolicy.RUNTIME)
// retention : 어느 시점까지 존재할 것인지 (옵션 source:그저표기용 class runtime)
// aop 구현할 때는 런타임 중에 어노테이션이 어딨는지 알아야해서 runtime으로 해주기
public @interface LogExecutionTime {
}
