package com.doducvinh.base.common.exception;

import com.doducvinh.base.common.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Hứng các lỗi nghiệp vụ do mình tự ném ra (AppException, ResourceNotFound,
    // ...)
    @ExceptionHandler(AppException.class)
    public ResponseEntity<RestResponse<Object>> handleAppException(AppException ex) {
        log.warn("Business Exception [{}]: {}", ex.getStatus(), ex.getMessage());
        return ResponseEntity
                .status(ex.getStatus())
                .body(RestResponse.error(ex.getStatus().value(), ex.getMessage()));
    }

    // 2. Hứng lỗi validation (@Valid ở DTO): trả về chi tiết field nào bị lỗi
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponse<Map<String, String>>> handleValidationException(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        log.warn("Validation failed: {}", errors);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(RestResponse.error(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors));
    }

    // 3. Hứng tất cả các lỗi server chưa biết (500): log đầy đủ để dev debug, nhưng
    // không lộ stack trace ra ngoài
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestResponse<Object>> handleGenericException(Exception ex) {
        log.error("Unhandled Exception: ", ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(RestResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "An unexpected internal server error occurred"));
    }
}