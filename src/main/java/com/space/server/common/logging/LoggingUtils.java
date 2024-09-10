package com.space.server.common.logging;

import com.space.server.common.exception.SpaceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Slf4j
public class LoggingUtils {
    public static void warn(SpaceException exception) {
        String message = getExceptionMessage(exception.getErrorCode().getMessage());
        log.warn(message + "\n \t {}", exception);
    }

    public static void warn(MethodArgumentNotValidException exception) {
        String message = getExceptionMessage(exception.getMessage());
        log.warn(message + "\n \t {}", exception);
    }

    private static String getExceptionMessage(String message) {
        if (message == null || message.isBlank()) {
            return "";
        }
        return message;
    }

    public static void error(RuntimeException exception) {
        String message = getExceptionMessage(exception.getMessage());
        log.error(message + "\n \t {}", exception);
    }
}