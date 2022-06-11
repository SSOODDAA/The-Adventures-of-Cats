package com.example.caveadventure.service.ex;

/**
 * 业务异常基类
 */

public class ServiceException extends RuntimeException {
    public ServiceException(){
        super();
    }

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(Throwable cause){
        super(cause);
    }

    public ServiceException(String message, Throwable cause){
        super(message, cause);
    }

    protected ServiceException(String message, Throwable cause,
                            boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
