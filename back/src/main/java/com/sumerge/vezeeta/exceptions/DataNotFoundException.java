package com.sumerge.vezeeta.exceptions;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(Class<?> cls, Integer id) {
        super(String.format("Entity %s with id %d is not found", cls.getName(), id));
    }
}
