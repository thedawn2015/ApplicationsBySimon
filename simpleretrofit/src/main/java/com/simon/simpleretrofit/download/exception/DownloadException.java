package com.simon.simpleretrofit.download.exception;

/**
 * 自定义异常处理
 * Created by xw on 2016/7/29.
 */
public class DownloadException extends Exception {

    public DownloadException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
