package com.bell.renting.cars.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Car {
    @JsonProperty("carId")
    private String carId;
    @JsonProperty("registrationNumber")
    private String registrationNumber;
    @JsonProperty("model")
    private String model;
    @JsonProperty("isRented")
    private boolean isRented;
    @JsonProperty("clientId")
    private String clientId;


    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String renter) {
        this.clientId = renter;
    }


}
