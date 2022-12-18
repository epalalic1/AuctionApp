package com.developer.auctionapp.exception;

/**
 * Class UserAlreadyExistException extends Exception, so we use it when we notify that the user
 * we want to register must change email because there is already user with that email address in database
 */

public class UserAlreadyExistException extends Exception {

    public UserAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
