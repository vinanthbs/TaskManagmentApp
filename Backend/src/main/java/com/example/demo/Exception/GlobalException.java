package com.example.demo.Exception;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity <Object> handleTaskNotFoundException( TaskNotFoundException tn){
        HashMap<String, Object> body= new HashMap<>();
        body.put("status", HttpStatus.NOT_FOUND);
        body.put("message", tn.getMessage());
        body.put("timeStamp", LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundExeption.class)
    public ResponseEntity <Object> handleUserNotFoundException( UserNotFoundExeption un){
        HashMap<String, Object> body= new HashMap<>();
        body.put("status", HttpStatus.NOT_FOUND);
        body.put("message", un.getMessage());
        body.put("timeStamp", LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Object> handleInvalidCredentialsException(
            InvalidCredentialsException ex){
        HashMap<String,Object> body = new HashMap<>();
        body.put("status", HttpStatus.UNAUTHORIZED.value());
        body.put("message", ex.getMessage());
        body.put("timeStamp", LocalDateTime.now());
        return new ResponseEntity<>(body,HttpStatus.UNAUTHORIZED);
    }

}
