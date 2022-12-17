package com.javainuse.util;


import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static <T> ResponseEntity<?> buildResponse(HttpStatus httpStatus, T data, String message) {
        var serviceResult = new ServiceResult<>(httpStatus, message, data);
        return ResponseEntity.status(httpStatus).body(serviceResult);
    }

    public static <T> ResponseEntity<?> created(T data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    public static ResponseEntity<?> noContent() {
        return ResponseEntity.noContent().build();
    }
    
    public static <T> ResponseEntity<?> noContent(String message) {
        var serviceResult = new ServiceResult<>(HttpStatus.NO_CONTENT, message);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(serviceResult);
    }

    public static <T> ResponseEntity<?> badRequest(T data, String message) {
        var serviceResult = new ServiceResult<>(HttpStatus.BAD_REQUEST, message, data);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(serviceResult);
    }

    public static <T> ResponseEntity<?> ok(T data, String message) {
        var serviceResult = new ServiceResult<>(HttpStatus.OK, message, data);
        return ResponseEntity.status(HttpStatus.OK).body(serviceResult);
    }

    public static <T> ResponseEntity<?> ok(T data) {
        var serviceResult = new ServiceResult<>(HttpStatus.OK, data);
        return ResponseEntity.status(HttpStatus.OK).body(serviceResult);
    }

    private ResponseUtil() {
    }
}
