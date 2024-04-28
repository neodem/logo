package com.neodem.logo.processors;

public class SyntaxError extends Exception {

    /**
     *
     */
    public SyntaxError() {
        super();
    }

    /**
     * @param message
     */
    public SyntaxError(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public SyntaxError(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public SyntaxError(Throwable cause) {
        super(cause);
    }
}
