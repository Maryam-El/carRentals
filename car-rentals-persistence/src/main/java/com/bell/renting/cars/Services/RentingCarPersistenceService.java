package com.bell.renting.cars.Services;

import com.bell.renting.cars.dao.CarDAO;
import com.bell.renting.cars.dao.CarRepository;
import com.bell.renting.cars.dao.ClientDAO;
import com.bell.renting.cars.dao.ClientRepository;
import com.bell.renting.cars.exception.RentingCarsPersistenceException;
import com.bell.renting.cars.model.Car;
import com.bell.renting.cars.model.Client;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RentingCarPersistenceService {
    @Autowired
    CarRepository carRepository;
    @Autowired
    ClientRepository clientRepository;

    public void patchCar(String idCar, Car car) throws RentingCarsPersistenceException {
        Optional<CarDAO> carOptional = carRepository.findById(idCar);
        if(carOptional.isPresent()) {
            CarDAO modifiedCar = new CarDAO();
            modifiedCar.setCarId(idCar);
            modifiedCar.setRented(car.isRented());
            modifiedCar.setClientId(car.getClientId());
            modifiedCar.setRegistrationNumber(car.getRegistrationNumber() != null ? car.getRegistrationNumber():carOptional.get().getRegistrationNumber());
            modifiedCar.setModel(car.getModel() != null ? car.getModel():carOptional.get().getModel());
            try {
                carRepository.save(modifiedCar);
            }catch(RuntimeException e){
                throw new RentingCarsPersistenceException(HttpStatus.INTERNAL_SERVER_ERROR,"Could not save the changes");
            }
        }else{
            throw new RentingCarsPersistenceException(HttpStatus.NOT_FOUND, "The Car does not exist");
        }
    }

    public void patchClient(String clientId, Client client) throws RentingCarsPersistenceException {
        Optional<ClientDAO> clientOptional = clientRepository.findById(clientId);
        if(clientOptional.isPresent()) {
            ClientDAO modifiedClient = new ClientDAO();
            modifiedClient.setIdClient(clientId);
            modifiedClient.setFirstNameClient(client.getClientFirstName() != null ? client.getClientFirstName():clientOptional.get().getFirstNameClient());
            modifiedClient.setLastNameClient(client.getClientLastName() != null ? client.getClientLastName():clientOptional.get().getLastNameClient());
            try {
                clientRepository.save(modifiedClient);
            }catch(RuntimeException e){
                throw new RentingCarsPersistenceException(HttpStatus.INTERNAL_SERVER_ERROR,"Could not save the changes");
            }
        }else{
            throw new RentingCarsPersistenceException(HttpStatus.NOT_FOUND, "The client does not exist");
        }
    }

    public void postClient(String id,Client client) throws RentingCarsPersistenceException {
            ClientDAO newClient = new ClientDAO();
            newClient.setIdClient(id);
            newClient.setFirstNameClient(client.getClientFirstName());
            newClient.setLastNameClient(client.getClientLastName());
            try {
                clientRepository.save(newClient);
            }catch(RuntimeException e) {
                throw new RentingCarsPersistenceException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not save the changes");
            }
    }

    public List<CarDAO> getRentedCars(){
        return carRepository.findCarDAOByRentedIsTrue();
    }

    public CarDAO getCar(String carId){
        Optional<CarDAO> carOptional = carRepository.findById(carId);
        if(carOptional.isPresent()){
            return carOptional.get();
        }else{
           throw new RentingCarsPersistenceException(HttpStatus.NOT_FOUND, "The car does not exist");
        }
    }

    public ClientDAO getClient(String clientId){
        Optional<ClientDAO> carOptional = clientRepository.findById(clientId);
        return carOptional.orElse(null);
    }

}
