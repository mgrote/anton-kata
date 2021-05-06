/*
 * File: Rental
 * Created on 09.09.2020
 *
 * Copyright (c) 2020 by Daimler AG
 */
package com.dailyrental.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Michael Grote, GROTEMI (415)
 */
public class Rental {
    private RentalType rentalType;
    private Vehicle vehicle;
    private Customer customer;
    private LocalDateTime start;
    private LocalDateTime end;
    private VehicleMovement movement;
    private List<Bill> bills;

    public RentalType getRentalType() {
        return rentalType;
    }

    public void setRentalType(RentalType rentalType) {
        this.rentalType = rentalType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public VehicleMovement getMovement() {
        return movement;
    }

    public void setMovement(VehicleMovement movement) {
        this.movement = movement;
    }

    public void addBill(Bill bill) {
        bills.add(bill);
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return rentalType == rental.rentalType &&
                Objects.equals(vehicle, rental.vehicle) &&
                Objects.equals(customer, rental.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentalType, vehicle, customer);
    }

    {
        movement = new VehicleMovement();
        bills = new ArrayList<>();
    }
}
