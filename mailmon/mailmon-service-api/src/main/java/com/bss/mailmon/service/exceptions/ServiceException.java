package com.bss.mailmon.service.exceptions;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	private int code = 0;

	public ServiceException(final int code) {
		this.code = code;
	}

	public ServiceException(final String message) {
		super(message);
	}

	public ServiceException(final int code, final String message) {
		super(message);
		this.code = code;
	}

	public ServiceException(final int code, final Throwable cause) {
		super(cause);
		this.code = code;
	}

	public ServiceException(final int code, final String message,
			final Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public ServiceException(final Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, final Throwable cause) {
		super(message, cause);
	}

	public int getCode() {
		return code;
	}

}
