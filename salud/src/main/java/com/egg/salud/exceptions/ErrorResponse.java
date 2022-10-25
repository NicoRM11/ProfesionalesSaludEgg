package com.egg.salud.exceptions;

import java.sql.Timestamp;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {
    
    private int status;
    private List<String> messages;
    private Timestamp timestamp;
    
    public ErrorResponse(){
      this.messages = new ArrayList<>();
    }
    
    public void add(String message){
        this.messages.add(message);
    }
    
    
}