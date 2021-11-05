package com.bell.renting.cars.controller;

import com.bell.renting.cars.exception.Error;
import com.bell.renting.cars.exception.RentingCarsPersistenceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

public class ExceptionHandlerControllerTest {

    @InjectMocks
    ExceptionHandlerController exceptionHandlerController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void manageSystemErrorTest(){
        MockHttpServletResponse httpResponse = new MockHttpServletResponse();
        RentingCarsPersistenceException exception = new RentingCarsPersistenceException(HttpStatus.BAD_REQUEST, "message");

        Error response = exceptionHandlerController.manageSystemError(exception,httpResponse);
        Assert.assertEquals("message",response.getMessage());
    }

}
