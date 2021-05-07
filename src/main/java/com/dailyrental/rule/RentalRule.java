/*
 * File: RentalRules
 * Created on 07.05.2021
 *
 * Copyright (c) 2021 by Daimler AG
 */
package com.dailyrental.rule;

import com.dailyrental.domain.Rental;
import com.dailyrental.domain.RentalType;
import java.util.function.Predicate;

/**
 * @author Michael Grote, GROTEMI (415)
 */
public interface RentalRule extends Predicate<Rental> {

    RentalRule isFavoriteCustomer = rental -> rental.getCustomer().isFav();

    RentalRule isGarageRental = rental -> rental.getRentalType() == RentalType.GARAGE_RENTAL;

    RentalRule isPrivateRental = rental -> rental.getRentalType() == RentalType.PRIVAT_RENTAL;

}
