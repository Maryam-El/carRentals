package bell.renting.cars.rentingcars.service;

import bell.renting.cars.rentingcars.exception.RentingCarsException;
import bell.renting.cars.rentingcars.model.Car;
import bell.renting.cars.rentingcars.model.Cars;
import bell.renting.cars.rentingcars.model.Client;
import bell.renting.cars.rentingcars.service.client.RentingCarsPersistenceClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static org.junit.jupiter.api.Assertions.*;

public class RentingCarServiceTest {

    @InjectMocks
    RentingCarService rentingCarService;

    @Mock
    RentingCarsPersistenceClient rentingCarsPersistenceClient;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void rentcarTest(){
        Car car = new Car();
        car.setRented(false);
        Client client = new Client();
        client.setClientId("clientId");
        Mockito.when(rentingCarsPersistenceClient.getCar(Mockito.anyString())).thenReturn(Mono.just(car));
        Mockito.when(rentingCarsPersistenceClient.getClient(Mockito.anyString())).thenReturn(Mono.just(client));
        rentingCarService.rentCar("id",client);
        Mockito.verify(rentingCarsPersistenceClient, Mockito.times(1)).patchCar(Mockito.anyString(),Mockito.any());

    }

    @Test
    public void rentcarExceptionTest(){
        Car car = new Car();
        car.setRented(true);
        Client client = new Client();
        client.setClientId("clientId");
        Mockito.when(rentingCarsPersistenceClient.getCar(Mockito.anyString())).thenReturn(Mono.just(car));
        try {
            rentingCarService.rentCar("id", client);
            Assert.fail("An exception had to be thrown");
        }catch (RentingCarsException ex){
            Assert.assertEquals(HttpStatus.BAD_REQUEST,ex.getHttpStatus());
            Assert.assertEquals("The car is already rented",ex.getMessage());
        }

    }

    @Test
    public void returnCarTest(){
        Car car = new Car();
        car.setRented(true);
        Client client = new Client();
        client.setClientId("clientId");
        Mockito.when(rentingCarsPersistenceClient.getCar(Mockito.anyString())).thenReturn(Mono.just(car));
        rentingCarService.returnCar("id");
        Mockito.verify(rentingCarsPersistenceClient, Mockito.times(1)).patchCar(Mockito.anyString(),Mockito.any());

    }

    @Test
    public void returncarExceptionTest(){
        Car car = new Car();
        car.setRented(false);
        Client client = new Client();
        client.setClientId("clientId");
        Mockito.when(rentingCarsPersistenceClient.getCar(Mockito.anyString())).thenReturn(Mono.just(car));
        try {
            rentingCarService.returnCar("id");
            Assert.fail("An exception had to be thrown");
        }catch (RentingCarsException ex){
            Assert.assertEquals(HttpStatus.BAD_REQUEST,ex.getHttpStatus());
            Assert.assertEquals("The car is not rented",ex.getMessage());
        }

    }

    @Test
    public void getRentedCars(){
        Car car = new Car();
        car.setRented(true);
        Mockito.when(rentingCarsPersistenceClient.getRentedCars()).thenReturn(Flux.just(car));
        Cars cars= rentingCarService.getRentedCars();
        Assert.assertEquals(car,cars.getCars().get(0));
    }

}
