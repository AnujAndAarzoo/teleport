package com.teleport.logistics.exceptions;

public class LogisticsAppException extends RuntimeException {

	private static final long serialVersionUID = 1031825232388618773L;

	public LogisticsAppException() {
		super();
	}

	public LogisticsAppException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public LogisticsAppException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public LogisticsAppException(String arg0) {
		super(arg0);
	}

	public LogisticsAppException(Throwable arg0) {
		super(arg0);
	}

}
