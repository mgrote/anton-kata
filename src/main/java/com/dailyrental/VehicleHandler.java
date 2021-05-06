/*
 * File: VehicleHandler
 * Created on 09.09.2020
 *
 * Copyright (c) 2020 by Daimler AG
 */
package com.dailyrental;

import com.dailyrental.domain.Rental;
import com.dailyrental.domain.RentalType;
import com.dailyrental.domain.VehicleMovement;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Grote, GROTEMI (415)
 */
@Component
public class VehicleHandler {
    @Autowired
    private RentalService rentalService;

    /**
     * There are two types of rental with different rules:
     * RentalType.GARAGE_RENTAL for business users
     * RentalType.PRIVAT_RENTAL for private users
     * The following rules apply:
     * GARAGE_RENTAL:
     * In case of premature checkout greater than 2 hours the rental end will reduced by 2 hours (2minusRule).
     * In case of premature checkout greater than 4 hours the rental end will reduced by 4 hours (4minusRule).
     * In case of late checkout greater than 2 hours the rental end will extend by 2 hours (2plusRule).
     * In case of late checkout greater than 4 hours the rental end will extend by 4 hours (4plusRule).
     * In case of a favorite customer no changes will apply.
     * PRIVATE_RENTAL:
     * In case of premature checkout the rental end will reduced by the difference between checkout and expected start in hours.
     * In case of a favorite customer no changes will apply.
     *
     * @param rental Rental with information about timeranges and customer
     * @param checkoutDate the real vehicle checkout time
     */
    public void checkout(Rental rental, LocalDateTime checkoutDate) {
        var movement = new VehicleMovement();
        movement.setCheckout(checkoutDate);
        rental.setMovement(movement);
        if(rental.getRentalType() == RentalType.GARAGE_RENTAL
                && rental.getStart().isAfter(checkoutDate.plusHours(2))) {
            rental.setEnd(rental.getEnd().minusHours(2));
        } else if (rental.getRentalType() == RentalType.PRIVAT_RENTAL
            && rental.getStart().isAfter(checkoutDate)) {
            rental.setEnd(rental.getEnd().minusHours((long) rental.getStart().getHour() - checkoutDate.getHour()));
        }
        rentalService.updateRental(rental);
    }

    public void checkin(Rental rental) {

    }
}
