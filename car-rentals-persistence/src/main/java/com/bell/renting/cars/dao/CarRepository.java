package com.bell.renting.cars.dao;

import com.bell.renting.cars.model.Car;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<CarDAO,String> {
    List<CarDAO> findCarDAOByRentedIsTrue();

}
