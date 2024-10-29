package com.space.server.common.exception;

import com.space.server.common.exception.security.SpaceSecurityException;
import com.space.server.common.logging.LoggingUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SpaceException.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(SpaceException exception) {
        LoggingUtils.warn(exception);

        ErrorResponse response = ErrorResponse.from(exception);;

        return ResponseEntity
                .status(exception.getStatus())
                .body(response);
    }

    @ExceptionHandler(SpaceSecurityException.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(SpaceSecurityException exception) {
        LoggingUtils.warn(exception);

        int httpStatus = exception.getStatus().value();
        String errorCode = exception.getErrorCode();
        String message = exception.getMessage();
        ErrorResponse response = ErrorResponse.from(httpStatus, errorCode, message);;

        return ResponseEntity
                .status(httpStatus)
                .body(response);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorResponse> handleDefineException(MethodArgumentTypeMismatchException exception) {
        LoggingUtils.warn(exception);

        return ResponseEntity.status(400)
            .body(ErrorResponse.from(400, "INVALID_INPUT" ,exception.getMessage()));
    }


    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleDefineException(IllegalArgumentException exception) {
        LoggingUtils.warn(exception);

        String message;

        if (exception.getCause() == null) {
            message = "";
        } else {
            message = exception.getCause().getMessage();
        }

        return ResponseEntity.status(400)
                .body(ErrorResponse.from(400, "INVALID_INPUT" ,"잘못된 값이 들어왔습니다."));
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<ErrorResponse> handleDefineException(NullPointerException exception) {
        LoggingUtils.warn(exception);

        String message;

        if (exception.getCause() == null) {
            message = "";
        } else {
            message = exception.getCause().getMessage();
        }

        return ResponseEntity.status(400)
            .body(ErrorResponse.from(400, "INVALID_INPUT" ,message));
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorResponse> handleDefineException(RuntimeException exception) {
        LoggingUtils.error(exception);

        return ResponseEntity.status(500)
                .body(ErrorResponse.from(500, "SERVER_UNKNOWN","서버에서 알 수 없는 에러가 발생했습니다."));
    }
}
