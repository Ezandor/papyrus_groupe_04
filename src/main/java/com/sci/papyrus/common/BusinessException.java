package com.sci.papyrus.common;

public class BusinessException extends CommonsException {

    public BusinessException(){
        super();
    }

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(Throwable throwable){
        super(throwable);
    }

    public BusinessException(String message, Throwable throwable){
        super(message, throwable);
    }

}
