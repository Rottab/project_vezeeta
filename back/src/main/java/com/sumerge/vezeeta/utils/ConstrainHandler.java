package com.sumerge.vezeeta.utils;

import com.sumerge.vezeeta.exceptions.DataConstrainViolationException;
import org.springframework.dao.DataIntegrityViolationException;

public class ConstrainHandler {
    final public static String EMAIL_USED = "email_used";
    final public static String USERNAME_USED = "username_used";
    final public static String UNKNOWN = "unknown";

    public static Object handle(String message) {
        if (message.contains(ConstrainHandler.USERNAME_USED)) {
            throw new DataConstrainViolationException(ConstrainHandler.USERNAME_USED);
        }
        if (message.contains(ConstrainHandler.EMAIL_USED)) {
            throw new DataConstrainViolationException(ConstrainHandler.EMAIL_USED);
        }
        throw new DataConstrainViolationException(ConstrainHandler.UNKNOWN);
    }
}
