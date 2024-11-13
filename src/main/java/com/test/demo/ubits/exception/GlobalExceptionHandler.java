package com.test.demo.ubits.exception;

import jakarta.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final String DETAILS = "details";
  private static final String MESSAGE = "message";

  // Manejo de excepciones para entidades no encontradas
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex,
      WebRequest request) {
    Map<String, Object> response = new HashMap<>();
    response.put(MESSAGE, "Resource not found");
    response.put(DETAILS, ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  // Manejo de excepciones cuando el argumento no es válido
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex,
      WebRequest request) {
    Map<String, Object> response = new HashMap<>();
    response.put(MESSAGE, "Invalid argument");
    response.put(DETAILS, ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  // Manejo de excepciones para errores de tipo de argumento incorrecto
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
      MethodArgumentTypeMismatchException ex, WebRequest request) {
    if (ex.getName().equals("channel")) {
      return ResponseEntity.badRequest()
          .body("Invalid channel. Allowed values are 'WEB' and 'MOBILE'.");
    }
    Map<String, Object> response = new HashMap<>();
    response.put(MESSAGE, "Type mismatch");
    response.put(DETAILS, ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  // Manejo de excepciones genéricas
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
    Map<String, Object> response = new HashMap<>();
    response.put(MESSAGE, "An unexpected error occurred");
    response.put(DETAILS, ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
