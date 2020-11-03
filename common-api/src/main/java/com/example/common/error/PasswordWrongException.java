package com.example.common.error;

public class PasswordWrongException extends RuntimeException {
    public PasswordWrongException(){
        super("password is wrong");
    }
}
