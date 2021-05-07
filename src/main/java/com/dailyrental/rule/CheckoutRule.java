/*
 * File: CheckoutRule
 * Created on 07.05.2021
 *
 * Copyright (c) 2021 by Daimler AG
 */
package com.dailyrental.rule;

import com.dailyrental.domain.Rental;
import java.time.LocalDateTime;
import java.util.function.BiPredicate;

/**
 * @author Michael Grote, GROTEMI (415)
 */
public interface CheckoutRule extends BiPredicate<Rental, LocalDateTime> {

    CheckoutRule isLate = (rental, cod) -> rental.getStart().isBefore(cod);

    CheckoutRule isEarly = (rental, cod) -> rental.getStart().isAfter(cod);

    CheckoutRule twoMinusRule = (rental, cod) -> rental.getStart().isAfter(cod.plusHours(2));

    CheckoutRule fourMinusRule = (rental, cod) -> rental.getStart().isAfter(cod.plusHours(4));

    CheckoutRule twoMinusRuleApplies = (rental, cod) -> twoMinusRule.and(fourMinusRule.negate()).test(rental, cod);

    CheckoutRule fourMinusRuleApplies = fourMinusRule;

    CheckoutRule isNotFavoriteCustomer = (rental, cod) -> RentalRule.isFavoriteCustomer.negate().test(rental);

    CheckoutRule isGarageRental = (rental, cod) -> RentalRule.isGarageRental.test(rental);

    CheckoutRule isPrivateRental = (rental, cod) -> RentalRule.isPrivateRental.test(rental);
}
