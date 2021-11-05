package com.bell.renting.cars.exception;

import org.springframework.http.HttpStatus;

public class RentingCarsPersistenceException extends RuntimeException {

    private HttpStatus httpStatus;

    public RentingCarsPersistenceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
