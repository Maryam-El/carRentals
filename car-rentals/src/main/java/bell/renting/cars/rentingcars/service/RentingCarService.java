package bell.renting.cars.rentingcars.service;

import bell.renting.cars.rentingcars.dao.CarRepository;
import bell.renting.cars.rentingcars.exception.RentingCarsException;
import bell.renting.cars.rentingcars.model.Car;
import bell.renting.cars.rentingcars.model.Cars;
import bell.renting.cars.rentingcars.model.Client;
import bell.renting.cars.rentingcars.service.client.RentingCarsPersistenceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RentingCarService {
    @Autowired
    RentingCarsPersistenceClient rentingCarsPersistenceClient;
    @Autowired
    CarRepository carRepository;

    public void rentCar(String carId, Client client) {
        if(!isCarRented(carId)) {
            Car car = new Car();
            car.setRented(true);
            car.setClientId(client.getClientId());
            createClientIfNotExistant(client);
            rentingCarsPersistenceClient.patchCar(carId, car);
        }else {
            throw new RentingCarsException(HttpStatus.BAD_REQUEST,"The car is already rented");
        }
    }

    public void returnCar(String carId) {
        if(isCarRented(carId)) {
            Car car = new Car();
            car.setRented(false);
            car.setClientId(null);
            rentingCarsPersistenceClient.patchCar(carId, car);
        }else {
            throw new RentingCarsException(HttpStatus.BAD_REQUEST,"The car is not rented");
        }
    }

    public Cars getRentedCars(){
        Cars cars = new Cars();
        cars.setCars(rentingCarsPersistenceClient.getRentedCars().collectList().block());
        return cars;
    }

    private boolean isCarRented(String carId){
        Car car = rentingCarsPersistenceClient.getCar(carId).block();
        if(car != null){
            return car.isRented();
        }else {
            throw new RentingCarsException(HttpStatus.INTERNAL_SERVER_ERROR, "could not get the car from renting-car-persistance");
        }
    }

    private void createClientIfNotExistant(Client client){
        Client existantClient = rentingCarsPersistenceClient.getClient(client.getClientId()).block();
        if(existantClient == null){
            rentingCarsPersistenceClient.createClient(client.getClientId(),client);
        }
    }
}
