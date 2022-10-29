package com.egg.salud.exceptions;

import java.sql.Timestamp;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {

        ErrorResponse error = buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDataNotFoundException(DataNotFoundException e){
       
        ErrorResponse error = buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error , HttpStatus.NOT_FOUND);
    
    }
    
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e){
        ErrorResponse error = buildErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(error , HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e){
        ErrorResponse error = buildErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(error , HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(value = UserIsExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserIsExistException(UserIsExistsException e){
       ErrorResponse error = buildErrorResponse(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
       return new ResponseEntity<>(error , HttpStatus.NOT_ACCEPTABLE);
    }

    private ErrorResponse buildErrorResponse(String message, HttpStatus httpStatus) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(httpStatus.value());
        errorResponse.add(message);
        errorResponse.setTimestamp(Timestamp.from(Instant.now()));

        return errorResponse;

    }
}