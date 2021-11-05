package bell.renting.cars.rentingcars.dao;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CarRepository extends CrudRepository<CarDAO,String> {
    List<CarDAO> findCarDAOByRentedIsTrue();

}
