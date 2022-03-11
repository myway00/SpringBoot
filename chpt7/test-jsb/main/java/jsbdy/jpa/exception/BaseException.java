package jsbdy.jpa.exception;

public class BaseException extends RuntimeException{
    //제네레이트 -> overridemethod -> runtimeexception
    public BaseException(String message) {
        super(message);
    }
}
