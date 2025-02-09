package com.kkuzmin.demo.exception;

public class UserNotExistsException extends RuntimeException {

    public UserNotExistsException(String username) {
        super("User [%s] does not exist.".formatted(username));
    }
}
