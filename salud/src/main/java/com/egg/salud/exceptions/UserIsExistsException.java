package com.egg.salud.exceptions;

public class UserIsExistsException extends RuntimeException {
   
    public UserIsExistsException(String message){
     super(message);
    }
    
}