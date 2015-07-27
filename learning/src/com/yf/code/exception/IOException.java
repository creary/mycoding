package com.yf.code.exception;

public class IOException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 20150524L;
	public IOException() {
		super();
	}
	public IOException(String massgae) {
		super(massgae);
	}
	public IOException(String massgae,Throwable cause) {
		super(massgae,cause);
	}
	public IOException(Throwable cause) {
		super(cause);
	}
}
