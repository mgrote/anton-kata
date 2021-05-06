package com.dailyrental;
/*
 * File: com.dailyrental.RentalService
 * Created on 09.09.2020
 *
 * Copyright (c) 2020 by Daimler AG
 */

import com.dailyrental.domain.Customer;
import com.dailyrental.domain.Rental;
import com.dailyrental.domain.RentalType;
import com.dailyrental.domain.Vehicle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * @author Michael Grote, GROTEMI (415)
 */
@Service
public class RentalService {
    private List<Rental> rentals;
    private List<Customer> customers;
    private List<Vehicle> vehicles;

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public List<Rental> getRentalsByRentalType(RentalType type) {
        return rentals.stream()
                .filter(r -> r.getRentalType() == type)
                .collect(Collectors.toList());
    }

    public List<Rental> getRentalsByCustomer(Customer customer) {
        return rentals.stream()
                .filter(r -> r.getCustomer().equals(customer))
                .collect(Collectors.toList());
    }

    public void updateRental(Rental rental) {
        rentals.add(rentals.indexOf(rental), rental);
    }

    public Optional<Customer> getCustomerByName(String name) {
        return customers.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst();
    }

    public Optional<Vehicle> getVehicleByPlate(String licensePlate) {
        return vehicles.stream()
                .filter(v -> v.getLicensePlate().equals(licensePlate))
                .findFirst();
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    {
        rentals = new ArrayList<>();
        customers = new ArrayList<>();
        vehicles = new ArrayList<>();
    }
}
