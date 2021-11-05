package com.bell.renting.cars.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class ClientDAO {
    @Id
    private String idClient;
    private String lastNameClient;
    private String firstNameClient;

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getLastNameClient() {
        return lastNameClient;
    }

    public void setLastNameClient(String lastNameClient) {
        this.lastNameClient = lastNameClient;
    }

    public String getFirstNameClient() {
        return firstNameClient;
    }

    public void setFirstNameClient(String firstNameClient) {
        this.firstNameClient = firstNameClient;
    }


}
