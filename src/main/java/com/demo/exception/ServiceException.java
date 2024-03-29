package com.demo.exception;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException {
    private String errMsg;

    public ServiceException() {
    }

    public ServiceException(String errMsg, String messageKey) {
        super(messageKey, null);
        this.errMsg = errMsg;
    }
}
