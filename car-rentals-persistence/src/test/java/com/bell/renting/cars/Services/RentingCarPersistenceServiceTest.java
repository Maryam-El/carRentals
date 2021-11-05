package com.bell.renting.cars.Services;

import com.bell.renting.cars.dao.CarDAO;
import com.bell.renting.cars.dao.CarRepository;
import com.bell.renting.cars.dao.ClientDAO;
import com.bell.renting.cars.dao.ClientRepository;
import com.bell.renting.cars.exception.RentingCarsPersistenceException;
import com.bell.renting.cars.model.Car;
import com.bell.renting.cars.model.Client;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;


public class RentingCarPersistenceServiceTest {
    @InjectMocks
    RentingCarPersistenceService rentingCarPersistenceService;

    @Mock
    CarRepository carRepositoryMock;

    @Mock
    ClientRepository clientRepositoryMock;


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void patchCar_InternalErrorTest(){
        CarDAO car= new CarDAO();
        car.setModel("Model");
        car.setRegistrationNumber("registrationNumber");
        car.setCarId("1");
        car.setRented(false);
        Mockito.when(carRepositoryMock.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(car));
        Mockito.when(carRepositoryMock.save(Mockito.any())).thenThrow(new RuntimeException());

        try {
            rentingCarPersistenceService.patchCar("1", new Car());
            Assert.fail("An exception had to be thrown");
        }catch(RentingCarsPersistenceException ex){
            Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,ex.getHttpStatus());
            Assert.assertEquals("Could not save the changes",ex.getMessage());
        }

    }

    @Test
    public void patchCar_CarNotFoundTest(){
        Mockito.when(carRepositoryMock.findById(Mockito.anyString())).thenReturn(java.util.Optional.empty());
        try {
            rentingCarPersistenceService.patchCar("1", new Car());
            Assert.fail("An exception had to be thrown");
        }catch(RentingCarsPersistenceException ex){
            Assert.assertEquals(HttpStatus.NOT_FOUND,ex.getHttpStatus());
            Assert.assertEquals("The Car does not exist",ex.getMessage());
        }
    }

    @Test
    public void patchClient_InternalErrorTest(){
        ClientDAO clientDAO= new ClientDAO();
        clientDAO.setIdClient("id");
        clientDAO.setFirstNameClient("first");
        clientDAO.setLastNameClient("last");
        Mockito.when(clientRepositoryMock.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(clientDAO));
        Mockito.when(clientRepositoryMock.save(Mockito.any())).thenThrow(new RuntimeException());

        try {
            rentingCarPersistenceService.patchClient("1", new Client());
            Assert.fail("An exception had to be thrown");
        }catch(RentingCarsPersistenceException ex){
            Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,ex.getHttpStatus());
            Assert.assertEquals("Could not save the changes",ex.getMessage());
        }

    }

    @Test
    public void patchClient_CarNotFoundTest(){
        Mockito.when(clientRepositoryMock.findById(Mockito.anyString())).thenReturn(java.util.Optional.empty());
        try {
            rentingCarPersistenceService.patchClient("1", new Client());
            Assert.fail("An exception had to be thrown");
        }catch(RentingCarsPersistenceException ex){
            Assert.assertEquals(HttpStatus.NOT_FOUND,ex.getHttpStatus());
            Assert.assertEquals("The client does not exist",ex.getMessage());
        }
    }

    @Test
    public void postClient_InternalErrorTest(){
        Mockito.when(clientRepositoryMock.save(Mockito.any())).thenThrow(new RuntimeException());

        try {
            rentingCarPersistenceService.postClient("1", new Client());
            Assert.fail("An exception had to be thrown");
        }catch(RentingCarsPersistenceException ex){
            Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,ex.getHttpStatus());
            Assert.assertEquals("Could not save the changes",ex.getMessage());
        }

    }

    @Test
    public void getRentedCarsTest(){
        List<CarDAO> rentedCars = new ArrayList<>();
        CarDAO carDAO = new CarDAO();
        carDAO.setCarId("id");
        rentedCars.add(carDAO);

        Mockito.when(carRepositoryMock.findCarDAOByRentedIsTrue()).thenReturn(rentedCars);
        Assert.assertEquals(rentedCars,rentingCarPersistenceService.getRentedCars());

    }

    @Test
    public void getCarTest(){
        CarDAO carDAO = new CarDAO();
        carDAO.setCarId("id");

        Mockito.when(carRepositoryMock.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(carDAO));
        Assert.assertEquals(carDAO,rentingCarPersistenceService.getCar("id"));

    }

    @Test
    public void getCar_NotFoundTest(){
        Mockito.when(carRepositoryMock.findById(Mockito.anyString())).thenReturn(java.util.Optional.empty());
        try {
            rentingCarPersistenceService.getCar("1");
            Assert.fail("An exception had to be thrown");
        }catch(RentingCarsPersistenceException ex){
            Assert.assertEquals(HttpStatus.NOT_FOUND,ex.getHttpStatus());
            Assert.assertEquals("The car does not exist",ex.getMessage());
        }
    }

    @Test
    public void getClientTest(){
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.setIdClient("id");

        Mockito.when(clientRepositoryMock.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(clientDAO));
        Assert.assertEquals(clientDAO,rentingCarPersistenceService.getClient("id"));

    }

    @Test
    public void getClient_NotFoundTest(){
        Mockito.when(clientRepositoryMock.findById(Mockito.anyString())).thenReturn(java.util.Optional.empty());
        Assert.assertNull(rentingCarPersistenceService.getClient("id"));
    }

}
