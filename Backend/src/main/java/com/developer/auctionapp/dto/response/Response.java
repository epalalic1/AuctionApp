package com.developer.auctionapp.dto.response;

/**
 * <p>Response</p>
 *
 * Data Transfer Object that we send to the frontend as response when the try to initialize database
 */

public class Response {
    private Long statusCode;

    private String message;

    public Response() {
    }

    public Response(final Long statusCode, final String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public Long getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Long statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
