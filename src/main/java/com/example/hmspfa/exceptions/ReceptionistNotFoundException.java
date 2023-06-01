package com.example.hmspfa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReceptionistNotFoundException extends  RuntimeException {
    public ReceptionistNotFoundException(String message){
        super(message);
    }
}
