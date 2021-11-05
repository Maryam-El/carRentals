package com.bell.renting.cars.controller;

import com.bell.renting.cars.exception.Error;
import com.bell.renting.cars.exception.RentingCarsPersistenceException;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = {RentingCarsPersistenceException.class})
    @ResponseBody
    public Error manageSystemError(final RentingCarsPersistenceException ex, HttpServletResponse response) {
        return manageError(ex,response, ex.getHttpStatus());
    }


    private Error manageError(final Exception ex, HttpServletResponse response, HttpStatus status) {
        response.setStatus(status.value());

        Error error = new Error();
        error.setStatus(status.value());

        error.setMessage(ex.getMessage());
        error.setTime(OffsetDateTime.now().atZoneSameInstant(ZoneId.of("America/Montreal")).toString());
        if (ex.getCause() != null && ex.getCause().getCause() != null) {
            error.setDetail(ex.getCause().getCause().toString());
        } else {
            error.setDetail(ex.getClass().toString() + " : " + ex.getStackTrace()[0]);
        }

        return error;
    }
}
