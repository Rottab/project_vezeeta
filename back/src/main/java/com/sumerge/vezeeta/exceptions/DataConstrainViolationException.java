package com.sumerge.vezeeta.exceptions;

// Need to separate the email from this exception
public class DataConstrainViolationException extends RuntimeException  {
    public DataConstrainViolationException(String key) {
        super(key);
    }
}
