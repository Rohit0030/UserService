package com.rohit.user.service.UserService.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){

        super("Resource not found on Server !!!!!!!!");

    }

    public ResourceNotFoundException(String message){

        super(message);
    }
}
