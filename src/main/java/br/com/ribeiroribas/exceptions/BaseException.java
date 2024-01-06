package br.com.ribeiroribas.exceptions;

public class BaseException extends RuntimeException {

    public BaseException(ErrorType errorType) {
        super(errorType.name());
    }

    public BaseException(ErrorType errorCode, Throwable cause) {
        super(errorCode.name(), cause);
    }

    public BaseException(String message){
        super(message);
    }
}
