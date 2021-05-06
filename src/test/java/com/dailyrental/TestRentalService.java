/*
 * File: TestRentalService
 * Created on 09.09.2020
 *
 * Copyright (c) 2020 by Daimler AG
 */
package com.dailyrental;

import com.dailyrental.domain.Customer;
import com.dailyrental.domain.CustomerType;
import com.dailyrental.domain.Rental;
import com.dailyrental.domain.Vehicle;
import java.time.LocalDateTime;
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
public class TestRentalService {
    @Autowired
    private RentalService rentalService;

    @Before
    public void setup() {
        rentalService.setCustomers(customers());
        rentalService.setVehicles(vehicles());
    }

    @Test
    public void testRentalServiceBasic() {
        assertThat(rentalService).isNotNull();
        assertThat(rentalService.getVehicles()).hasSize(4);
        assertThat(rentalService.getCustomers()).hasSize(4);
        assertThat(rentalService.getCustomerByName("Private1")).isPresent();
        assertThat(rentalService.getVehicleByPlate("ABC")).isPresent();
    }

    public void testRental() {
        Rental rental = new Rental();
        Customer rentalCustomer = rentalService.getCustomerByName("Private1").orElseThrow();
        rental.setCustomer(rentalCustomer);
        rental.setVehicle(rentalService.getVehicleByPlate("ABC").orElseThrow());
        rental.setStart(LocalDateTime.of(2020, 9, 9, 8, 0));
        rental.setEnd(LocalDateTime.of(2020, 9, 9, 18, 0));
        rentalService.addRental(rental);
        assertThat(rentalService.getRentals()).hasSize(1);
        rental.setStart(LocalDateTime.of(2020, 9, 9, 10, 0));
        rentalService.updateRental(rental);
        assertThat(rentalService.getRentals()).hasSize(1);
        assertThat(rentalService.getRentalsByCustomer(rentalCustomer)).hasSize(1);
        Rental reloaded = rentalService.getRentalsByCustomer(rentalCustomer).get(0);
        assertThat(reloaded.getStart()).isEqualTo(LocalDateTime.of(2020, 9, 9, 10, 0));
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
