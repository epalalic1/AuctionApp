package com.developer.auctionapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class UserAlreadyExistException extends Exception, so we use it when we notify that the user
 * we want to register must change email because there is already user with that email address in database
 */

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistException extends Exception {

    public UserAlreadyExistException(final String errorMessage) {
        super(errorMessage);
    }
}
