package bell.renting.cars.rentingcars.exception;

import org.springframework.http.HttpStatus;

public class RentingCarsException extends RuntimeException {

    private HttpStatus httpStatus;

    public RentingCarsException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
