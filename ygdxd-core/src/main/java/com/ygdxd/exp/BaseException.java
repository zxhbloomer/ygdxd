package com.ygdxd.exp;


/**
 * @author ygdxd_admin
 * @create 2017-08-16 下午3:36
 */
public class BaseException extends Exception{

    private static final long serialVersionUID = -8385160807015611950L;

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message, Exception e) {
        super(message ,e);
    }
}
