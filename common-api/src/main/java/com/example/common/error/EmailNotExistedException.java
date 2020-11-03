package com.example.common.error;

public class EmailNotExistedException extends RuntimeException{
    public EmailNotExistedException(String email){
        super("Email is not Registered" + email);
    }
}
