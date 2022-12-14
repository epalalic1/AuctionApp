package com.developer.auctionapp.exception;

public class UserAlreadyExistException extends Exception {

    public UserAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
