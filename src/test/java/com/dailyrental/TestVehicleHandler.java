/*
 * File: TestVehicleHandler
 * Created on 09.09.2020
 *
 * Copyright (c) 2020 by Daimler AG
 */
package com.dailyrental;

import com.dailyrental.domain.Customer;
import com.dailyrental.domain.CustomerType;
import com.dailyrental.domain.Rental;
import com.dailyrental.domain.RentalType;
import com.dailyrental.domain.Vehicle;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Michael Grote, GROTEMI (415)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TestVehicleHandler {
    @Autowired
    private RentalService rentalService;
    @Autowired
    private VehicleHandler vehicleHandler;

    @Before
    public void setup() {
        rentalService.setCustomers(customers());
        rentalService.setVehicles(vehicles());
        rentalService.setRentals(new ArrayList<>());
    }

    @Test
    public void testPrematureGarageCheckout() {
        Rental rental = new Rental();
        Customer rentalCustomer = rentalService.getCustomerByName("Garage1").orElseThrow();
        rental.setRentalType(RentalType.GARAGE_RENTAL);
        rental.setCustomer(rentalCustomer);
        rental.setVehicle(rentalService.getVehicleByPlate("ABC").orElseThrow());
        rental.setStart(LocalDateTime.of(2020, 9, 9, 10, 0));
        rental.setEnd(LocalDateTime.of(2020, 9, 9, 18, 0));
        rentalService.addRental(rental);
        vehicleHandler.checkout(rental, LocalDateTime.of(2020, 9, 9, 7, 0));
        Rental reloaded = rentalService.getRentalsByCustomer(rentalCustomer).get(0);
        assertThat(reloaded.getEnd()).isEqualTo(LocalDateTime.of(2020, 9, 9, 16, 0));
    }

    @Test
    public void testFairGarageCheckout() {
        Rental rental = new Rental();
        Customer rentalCustomer = rentalService.getCustomerByName("Garage1").orElseThrow();
        rental.setRentalType(RentalType.GARAGE_RENTAL);
        rental.setCustomer(rentalCustomer);
        rental.setVehicle(rentalService.getVehicleByPlate("ABC").orElseThrow());
        rental.setStart(LocalDateTime.of(2020, 9, 9, 10, 0));
        rental.setEnd(LocalDateTime.of(2020, 9, 9, 18, 0));
        rentalService.addRental(rental);
        vehicleHandler.checkout(rental, LocalDateTime.of(2020, 9, 9, 9, 0));
        Rental reloaded = rentalService.getRentalsByCustomer(rentalCustomer).get(0);
        assertThat(reloaded.getEnd()).isEqualTo(LocalDateTime.of(2020, 9, 9, 18, 0));
    }

    @Test
    public void testPrematurePrivateCheckout() {
        Rental rental = new Rental();
        Customer rentalCustomer = rentalService.getCustomerByName("Private1").orElseThrow();
        rental.setRentalType(RentalType.PRIVAT_RENTAL);
        rental.setCustomer(rentalCustomer);
        rental.setVehicle(rentalService.getVehicleByPlate("ABC").orElseThrow());
        rental.setStart(LocalDateTime.of(2020, 9, 9, 10, 0));
        rental.setEnd(LocalDateTime.of(2020, 9, 9, 18, 0));
        rentalService.addRental(rental);
        vehicleHandler.checkout(rental, LocalDateTime.of(2020, 9, 9, 9, 0));
        Rental reloaded = rentalService.getRentalsByCustomer(rentalCustomer).get(0);
        assertThat(reloaded.getEnd()).isEqualTo(LocalDateTime.of(2020, 9, 9, 17, 0));
    }

    @Test
    public void testFairPrivateCheckout() {
        Rental rental = new Rental();
        Customer rentalCustomer = rentalService.getCustomerByName("Private1").orElseThrow();
        rental.setRentalType(RentalType.PRIVAT_RENTAL);
        rental.setCustomer(rentalCustomer);
        rental.setVehicle(rentalService.getVehicleByPlate("ABC").orElseThrow());
        rental.setStart(LocalDateTime.of(2020, 9, 9, 8, 0));
        rental.setEnd(LocalDateTime.of(2020, 9, 9, 18, 0));
        rentalService.addRental(rental);
        vehicleHandler.checkout(rental, LocalDateTime.of(2020, 9, 9, 9, 30));
        Rental reloaded = rentalService.getRentalsByCustomer(rentalCustomer).get(0);
        assertThat(reloaded.getEnd()).isEqualTo(LocalDateTime.of(2020, 9, 9, 18, 0));
    }

    private List<Customer> customers() {
        return Stream.of(
                new Customer("Private1", CustomerType.PRIVATE),
                new Customer("Private2", CustomerType.PRIVATE),
                new Customer("Garage1", CustomerType.GARAGE),
                new Customer("Garage2", CustomerType.GARAGE))
                .collect(Collectors.toList());
    }

    private List<Vehicle> vehicles() {
        return Stream.of(
                new Vehicle("ABC", 10),
                new Vehicle("DEF", 15),
                new Vehicle("GHI", 20),
                new Vehicle("JKL", 25))
                .collect(Collectors.toList());
    }
}
