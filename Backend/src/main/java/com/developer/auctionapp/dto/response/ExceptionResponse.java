package com.developer.auctionapp.dto.response;

import java.time.LocalDateTime;

public class ExceptionResponse {
    private final LocalDateTime timestamp;

    private final String message;

    private final String detail;

    public ExceptionResponse(
            final LocalDateTime timestamp,
            final String message,
            final String detail) {
        this.timestamp = timestamp;
        this.message = message;
        this.detail = detail;
    }

    public ExceptionResponse () {
        this.timestamp = LocalDateTime.now();
        this.message = "";
        this.detail = "";
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }
}

