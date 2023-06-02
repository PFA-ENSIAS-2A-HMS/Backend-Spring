package com.example.hmspfa.exceptions;

public class BiometricDataNotFoundException extends RuntimeException{
    public BiometricDataNotFoundException(String message){
        super(message);
    }
}
