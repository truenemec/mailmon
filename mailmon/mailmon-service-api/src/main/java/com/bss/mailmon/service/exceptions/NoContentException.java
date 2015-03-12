package com.bss.mailmon.service.exceptions;

public class NoContentException extends Exception {
    private static final long serialVersionUID = 1L;

    public NoContentException() {
    }

    public NoContentException(String msg) {
        super(msg);
    }
}
