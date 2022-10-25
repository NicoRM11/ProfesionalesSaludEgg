package com.egg.salud.exceptions;


public class ResourceNotFoundException extends RuntimeException {
  
   public ResourceNotFoundException(String message){
     super(message);
   }
    
}