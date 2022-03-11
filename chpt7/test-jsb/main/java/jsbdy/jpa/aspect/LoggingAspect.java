package jsbdy.jpa.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect//이 클래스가 관점임 알려줌
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // 아래는 advice로써 작용할 함수다
    // 또한 어떤 joinpoint에 들어갈 지 정해주는 pointcut 필요 -> annotation으로 붙여주자
    @Around(value="@annotation(LogExecutionTime)") //이 어노테이션 주변 애들에서 얘가 작동
    //어떤 특정 포인트 기준으로 주변에서 작동하는 아이임 알려주기

    //조인포인트 : 관점이 시행될 한 지점
    //프로시딩 조인포인트 : 일반적인 조인포인트랑 다름
    public Object logExecuitonTime(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime= System.currentTimeMillis();
        Object proceed = joinPoint.proceed(); //조인포인트 객체 선언
        long execTime = System.currentTimeMillis()-startTime;
        logger.trace("method executed in {}", execTime);
        return proceed;
    }

    @Before(value="@annotation(LogArguments)")
    //특정 시점 이전에 뭐가 일어났는지 알려주는 거라서 따로 반환해줄 객체 음슴- void로~
    public void logArguments(JoinPoint joinPoint){
        MethodSignature methodSignature=(MethodSignature) joinPoint.getSignature();
        //함수가 어떤 모양 가지는지 알려주는 메소드 시그니처
        logger.trace("method description:[{}]",methodSignature.getMethod());
        logger.trace("method name: [{}]", methodSignature.getName());
        logger.trace("declaring class: [{}]", methodSignature.getDeclaringType());
        //어노테이션 이름에있는 파라미터 받아오기
        //조인포인트 시행 전 데려옴
        //얘에 있는 parameter을 우리가 가지고 싶은 것
        Object[] arguments = joinPoint.getArgs();
        if (arguments.length==0){
            logger.trace("no arguments");
        }
        for (Object argument:arguments){
            logger.trace("argument [{}]", argument);
        }
    }

    @AfterReturning(value="@annotation(LogReturn)",returning ="returnValue")
    //반환값이 존재하는 애가 리턴한 후에
    //반환값을 logResults의 인자로 넘겨줘야 함
    //returning에서 정의된이름을 가진 애한테 (지금은 아랫줄아이) 반환값 대입시킴
    public void logResults(JoinPoint joinPoint, Object returnValue){
        MethodSignature  methodSignature=(MethodSignature) joinPoint.getSignature();
        logger.trace("method name : [{}]", methodSignature.getName());
        logger.trace("return type : [{}]", methodSignature.getDeclaringTypeName());
        logger.trace("return value: [{}]", methodSignature, returnValue);
    }

}
