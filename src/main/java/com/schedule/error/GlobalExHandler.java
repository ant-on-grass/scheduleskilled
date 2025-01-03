package com.schedule.error;


import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;


@Slf4j
@RestControllerAdvice
public class GlobalExHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> MethodArgumentNotValidExceptionHandler(
      MethodArgumentNotValidException ex) {

    log.info("GlobalExHandler 1");
    Map<String, String> errorResponse = new HashMap<>();

    errorResponse.put("Error", ex.getClass().getSimpleName());
    errorResponse.put("Message", ex.getBindingResult().getFieldError().getDefaultMessage());

    return ResponseEntity.status(400).body(errorResponse);
  }


  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, String>> ExceptionHandler(Exception ex) {
    log.info("GlobalExHandler 2");

    Map<String, String> ErrorResponse = new HashMap<>();

    ErrorResponse.put("Error", ex.getClass().getSimpleName());

    ErrorResponse.put("Message", "정의하지않은 Exception 입니다.");

    return ResponseEntity.status(500).body(ErrorResponse);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public void HttpMessageNotReadableExceptionHandle(HttpMessageNotReadableException ex) {
    // 단순 로그만 남기고 별도 응답 처리 안 함
    System.out.println("WARN: " + ex.getMessage());
  }

  @ExceptionHandler(IncorrectResultSizeDataAccessException.class)
  public void IncorrectResultSizeDataAccessExceptionHandle(
      IncorrectResultSizeDataAccessException ex) {
    // 단순 로그만 남기고 별도 응답 처리 안 함
    System.out.println("WARN: " + ex.getMessage());
  }


  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<Map<String, String>> RuntimeExceptionHandlery(RuntimeException ex) {
    log.info("GlobalExHandler 3");
    Map<String, String> ErrorResponse = new HashMap<>();

    ErrorResponse.put("Error", ex.getClass().getSimpleName());
    ErrorResponse.put("Message", "정의하지않은 RuntimeException 입니다.");

    return ResponseEntity.status(500).body(ErrorResponse);
  }

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<Map<String, String>> ResponseStatusExceptionHandler(
      ResponseStatusException ex) {
    log.info("GlobalExHandler 4");
    Map<String, String> ErrorResponse = new HashMap<>();

    ErrorResponse.put("Error", ex.getClass().getSimpleName());
    ErrorResponse.put("Message", ex.getMessage());

    String httpStatusName = ex.getMessage().split(" ")[1];
    log.info("httpStatusName :{}", httpStatusName);
    HttpStatus httpStatus = HttpStatus.valueOf(httpStatusName);

    return ResponseEntity.status(httpStatus).body(ErrorResponse);
  }


}
