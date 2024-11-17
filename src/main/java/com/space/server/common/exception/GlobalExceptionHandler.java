package com.space.server.common.exception;

import com.space.server.common.exception.mail.SpaceMailException;
import com.space.server.common.exception.security.SpaceSecurityException;
import com.space.server.common.logging.LoggingUtils;
import com.space.server.domain.mail.exception.value.EmailVerificationStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

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

    @ExceptionHandler(SpaceMailException.class)
    public ModelAndView handleSpaceEmailException(SpaceMailException exception) {
        LoggingUtils.warn(exception);

        ModelAndView modelAndView = new ModelAndView("verification-result");

        String status;
        switch (exception.getErrorCode()) {
            case "EMAIL_TOKEN_NOT_FOUND":
                status = EmailVerificationStatus.NOT_FOUND.name();
                break;
            case "EMAIL_TOKEN_EXPIRED":
                status = EmailVerificationStatus.EXPIRED.name();
                break;
            case "EMAIL_TOKEN_ALREADY_VERIFIED":
                status = EmailVerificationStatus.ALREADY_VERIFIED.name();
                break;
            default:
                status = EmailVerificationStatus.ERROR.name();
        }

        modelAndView.addObject("status", status);

        return modelAndView;
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorResponse> handleDefineException(MethodArgumentTypeMismatchException exception) {
        LoggingUtils.warn(exception);

        return ResponseEntity.status(400)
                .body(ErrorResponse.from(400, "TYPE_MISMATCH", "파라미터 타입이 일치하지 않습니다."));
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleDefineException(IllegalArgumentException exception) {
        LoggingUtils.warn(exception);

        return ResponseEntity.status(400)
                .body(ErrorResponse.from(400, "INVALID_ARGUMENT", "잘못된 값이 들어왔습니다."));
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<ErrorResponse> handleDefineException(NullPointerException exception) {
        LoggingUtils.warn(exception);

        return ResponseEntity.status(400)
                .body(ErrorResponse.from(400, "NULL_VALUE", "필수 값이 누락되었습니다."));
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorResponse> handleDefineException(RuntimeException exception) {
        LoggingUtils.error(exception);

        return ResponseEntity.status(500)
                .body(ErrorResponse.from(500, "SERVER_UNKNOWN","서버에서 알 수 없는 에러가 발생했습니다."));
    }
}
