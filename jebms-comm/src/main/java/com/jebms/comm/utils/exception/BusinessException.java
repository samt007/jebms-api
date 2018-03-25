package com.jebms.comm.utils.exception;

/**
 * 业务异常.
 *
 * @author dcp
 */
@SuppressWarnings("serial")
public class BusinessException extends Exception {

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

}