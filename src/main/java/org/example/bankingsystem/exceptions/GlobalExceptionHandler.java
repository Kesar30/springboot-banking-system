package org.example.bankingsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //validation exception
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleInvalidArguments(MethodArgumentNotValidException e){
        Map<String,String> exceptions = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            exceptions.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return exceptions;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String , String> handleException(Exception e){
        Map<String,String> exceptions = new HashMap<>();
        exceptions.put("error" , "Something went wrong!");
        return exceptions;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String , String> handleUserNotFoundException(UserNotFoundException e){
        Map<String,String> exceptions = new HashMap<>();
        exceptions.put("error" , e.getMessage());
        return exceptions;
    }
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidPasswordException.class)
    public Map<String , String> handleInvalidPasswordException(InvalidPasswordException e){
        Map<String,String> exceptions = new HashMap<>();
        exceptions.put("error" , e.getMessage());
        return exceptions;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AccountNotFoundException.class)
    public Map<String , String> handleAccountNotFoundException(AccountNotFoundException e){
        Map<String,String> exceptions = new HashMap<>();
        exceptions.put("error" , e.getMessage());
        return exceptions;
    }
}
