package com.bell.renting.cars.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Client {
    @JsonProperty("clientId")
    private String clientId;
    @JsonProperty("clientLastName")
    private String clientLastName;
    @JsonProperty("clientFirstName")
    private String clientFirstName;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }


}
