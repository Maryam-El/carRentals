package com.bell.renting.cars.controller;

import com.bell.renting.cars.Services.RentingCarPersistenceService;
import com.bell.renting.cars.dao.CarDAO;
import com.bell.renting.cars.dao.ClientDAO;
import com.bell.renting.cars.exception.RentingCarsPersistenceException;
import com.bell.renting.cars.model.Car;
import com.bell.renting.cars.model.Client;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RentingCarsPersistenceController {
    @Autowired
    RentingCarPersistenceService rentingCarPersistenceService;

    @PatchMapping(value="/patchCar/{carId}",produces = { "application/json" })
    public ResponseEntity<Void> patchCar(@PathVariable("carId") String id, @RequestBody Car car) throws RentingCarsPersistenceException {
        rentingCarPersistenceService.patchCar(id,car);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value="/patchClient/{clientId}",produces = { "application/json" })
    public ResponseEntity<Void> patchClient(@PathVariable("clientId") String id, @RequestBody Client client) throws RentingCarsPersistenceException {
        rentingCarPersistenceService.patchClient(id,client);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value="/postClient/{clientId}",produces = { "application/json" })
    public ResponseEntity<Void> postClient(@PathVariable("clientId") String id,@RequestBody Client client){
        rentingCarPersistenceService.postClient(id,client);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value="/cars/rentedCars",produces = { "application/json" })
    public ResponseEntity<List<CarDAO>> getRentedCars() {
        return new ResponseEntity<>(rentingCarPersistenceService.getRentedCars(),HttpStatus.OK);
    }

    @GetMapping(value="/cars/{carId}",produces = { "application/json" })
    public ResponseEntity<CarDAO> getCar(@PathVariable("carId") String id) {
        return new ResponseEntity<>(rentingCarPersistenceService.getCar(id),HttpStatus.OK);
    }

    @GetMapping(value="/clients/{clientId}",produces = { "application/json" })
    public ResponseEntity<ClientDAO> getClient(@PathVariable("clientId") String id) {
        return new ResponseEntity<>(rentingCarPersistenceService.getClient(id),HttpStatus.OK);
    }
}
