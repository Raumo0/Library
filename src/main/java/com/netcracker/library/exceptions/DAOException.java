package com.netcracker.library.exceptions;

/**
 * Created by raumo0 on 17.11.16.
 */
public class DAOException extends Exception {
    public DAOException() {
        super();
    }

    public DAOException(String msg) {
        super(msg);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}