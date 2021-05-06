/*
 * File: Vehicle
 * Created on 09.09.2020
 *
 * Copyright (c) 2020 by Daimler AG
 */
package com.dailyrental.domain;

/**
 * @author Michael Grote, GROTEMI (415)
 */
public class Vehicle {
    private final String licensePlate;
    private final int price;

    public Vehicle(String licensePlate, int price) {
        this.licensePlate = licensePlate;
        this.price = price;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getPrice() {
        return price;
    }
}
