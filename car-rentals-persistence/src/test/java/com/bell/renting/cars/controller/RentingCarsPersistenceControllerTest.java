package com.bell.renting.cars.controller;

import com.bell.renting.cars.Services.RentingCarPersistenceService;
import com.bell.renting.cars.model.Car;
import com.bell.renting.cars.model.Client;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RentingCarsPersistenceControllerTest {
    @Mock
    RentingCarPersistenceService rentingCarPersistenceServiceMock;
    @InjectMocks
    RentingCarsPersistenceController rentingCarsPersistenceController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Mockito.doNothing().when(rentingCarPersistenceServiceMock).patchCar(Mockito.anyString(),Mockito.any(Car.class));
    }

    @Test
    public void patchCarEndPointTest(){
        Assert.assertEquals(rentingCarsPersistenceController.patchCar("id",new Car()),new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @Test
    public void patchClientEndPointTest(){
        Assert.assertEquals(rentingCarsPersistenceController.patchClient("id",new Client()),new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @Test
    public void postClientEndPointTest(){
        Assert.assertEquals(rentingCarsPersistenceController.postClient("id",new Client()),new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @Test
    public void getClientEndPointTest(){
        Assert.assertEquals(rentingCarsPersistenceController.getClient("id"),new ResponseEntity<>(HttpStatus.OK));
    }

    @Test
    public void getCarEndPointTest(){
        Assert.assertEquals(rentingCarsPersistenceController.getCar("id"),new ResponseEntity<>(HttpStatus.OK));
    }




}
