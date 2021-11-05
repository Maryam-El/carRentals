package bell.renting.cars.rentingcars.controller;

import bell.renting.cars.rentingcars.model.Cars;
import bell.renting.cars.rentingcars.model.Client;
import bell.renting.cars.rentingcars.service.RentingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RentingCarsController {

    @Autowired
    RentingCarService rentingCarService;

    @PatchMapping(value="/rentCar/{carId}",produces = { "application/json" })
    public ResponseEntity<Void> rentCar(@PathVariable("carId") String id, @RequestBody Client client) {
        rentingCarService.rentCar(id,client);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value="/returnCar/{carId}",produces = { "application/json" })
    public ResponseEntity<Void> returnCar(@PathVariable("carId") String id) {
        rentingCarService.returnCar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value="/rentedCars",produces = { "application/json" })
    public ResponseEntity<Cars> getRentedCars() {
        return new ResponseEntity<>(rentingCarService.getRentedCars(),HttpStatus.OK);
    }

}
