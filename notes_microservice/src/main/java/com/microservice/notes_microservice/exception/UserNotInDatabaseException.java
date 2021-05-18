package com.microservice.notes_microservice.exception;

public class UserNotInDatabaseException extends Exception{
    public UserNotInDatabaseException(String message){
        super(message);
    }
}
