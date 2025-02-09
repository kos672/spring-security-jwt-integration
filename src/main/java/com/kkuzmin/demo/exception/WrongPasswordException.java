package com.kkuzmin.demo.exception;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super("Provided password is wrong.");
    }
}
