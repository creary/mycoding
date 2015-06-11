package com.yf.util.quartz;

/**
 * 任务调度异常类
 * @author biezhi
 * @since 1.0
 */
public class QuartzException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public QuartzException() {
        super();
    }

    public QuartzException(String message) {
        super(message);
    }

    public QuartzException(Throwable cause) {
        super(cause);
    }

    public QuartzException(String message, Throwable cause) {
        super(message, cause);
    }

}
