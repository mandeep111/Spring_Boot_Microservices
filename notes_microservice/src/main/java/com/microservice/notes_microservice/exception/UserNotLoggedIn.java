package com.microservice.notes_microservice.exception;

public class UserNotLoggedIn extends Exception {
    public UserNotLoggedIn(String message){
        super(message);
    }
}
