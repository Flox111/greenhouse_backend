package com.maltsev.greenhouse.exception;

public class TokenRefreshException extends  RuntimeException{

    private static final long serialVersionUID = 1;

    public TokenRefreshException(String token, String message){
        super(String.format("Failed for [%s]: %s", token, message));
    }
}