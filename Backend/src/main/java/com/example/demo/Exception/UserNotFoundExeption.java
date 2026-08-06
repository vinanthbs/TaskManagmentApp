package com.example.demo.Exception;

public class UserNotFoundExeption extends RuntimeException{
    public UserNotFoundExeption(String message){
        super(message);
    }
}
