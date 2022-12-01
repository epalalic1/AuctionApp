package com.developer.auctionapp.dto.response;

public class Response {
    private Long statusCode;

    private String message;

    public Response() {
    }

    public Response(Long statusCode, String message) {
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
