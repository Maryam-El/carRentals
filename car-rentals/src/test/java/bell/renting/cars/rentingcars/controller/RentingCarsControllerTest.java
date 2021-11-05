package bell.renting.cars.rentingcars.controller;

import bell.renting.cars.rentingcars.model.Client;
import bell.renting.cars.rentingcars.service.RentingCarService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

public class RentingCarsControllerTest {
    @InjectMocks
    RentingCarsController rentingCarsController;

    @Mock
    RentingCarService rentingCarService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void rentCarTest(){
        Assert.assertEquals(new ResponseEntity<>(HttpStatus.NO_CONTENT),rentingCarsController.rentCar("id",new Client()));
    }

    @Test
    public void returnCarTest(){
        Assert.assertEquals(new ResponseEntity<>(HttpStatus.NO_CONTENT),rentingCarsController.returnCar("id"));
    }

    @Test
    public void getRentedCarsTest(){
        Assert.assertEquals(new ResponseEntity<>(HttpStatus.OK),rentingCarsController.getRentedCars());
    }


}
