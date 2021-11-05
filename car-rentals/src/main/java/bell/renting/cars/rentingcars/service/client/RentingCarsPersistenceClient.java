package bell.renting.cars.rentingcars.service.client;

import bell.renting.cars.rentingcars.exception.RentingCarsException;
import bell.renting.cars.rentingcars.dao.CarDAO;
import bell.renting.cars.rentingcars.model.Car;
import bell.renting.cars.rentingcars.model.Cars;
import bell.renting.cars.rentingcars.model.Client;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RentingCarsPersistenceClient {

    private final WebClient webClient=WebClient.create();
    @Value("${renting.cars.persitance.path}")
    private String baseUrl;

    public void patchCar(String id, Car car) throws RentingCarsException {
        Mono<HttpStatus> status ;
        try {
            status = webClient.patch().uri(baseUrl + "/patchCar/{carID}", id)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).body(BodyInserters.fromValue(car))
                    .exchange().map(ClientResponse::statusCode);
        } catch (Exception e) {
            throw new RentingCarsException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not save changes");
        }
        if (status.block() == HttpStatus.INTERNAL_SERVER_ERROR) {
            throw new RentingCarsException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not save changes");
        }
    }

    public Flux<Car> getRentedCars(){
        return webClient.get().uri(baseUrl+"/cars/rentedCars").retrieve().bodyToFlux(Car.class);
    }

    public Mono<Car> getCar(String id){
        try {
           return webClient.get().uri(baseUrl + "/cars/{carID}", id).retrieve().bodyToMono(Car.class);
        }catch(Exception ex){
            throw new RentingCarsException(HttpStatus.INTERNAL_SERVER_ERROR, "could not get the car from renting-car-persistance");
        }
    }

    public Mono<Client> getClient(String id){
        try {
            return webClient.get().uri(baseUrl + "/clients/{clientId}", id).retrieve().bodyToMono(Client.class);
        }catch(Exception ex){
            throw new RentingCarsException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in communicating with renting-car-persistance");
        }
    }

    public void createClient(String id, Client client){
        Mono<HttpStatus> status ;
        try {
            status = webClient.post().uri(baseUrl + "/postClient/{clientId}", id)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).body(BodyInserters.fromValue(client))
                    .exchange().map(ClientResponse::statusCode);
        } catch (Exception e) {
            throw new RentingCarsException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not save changes");
        }
        if (status.block() == HttpStatus.INTERNAL_SERVER_ERROR) {
            throw new RentingCarsException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not save changes");
        }
    }
}
