/*
 * File: VehicleMovement
 * Created on 09.09.2020
 *
 * Copyright (c) 2020 by Daimler AG
 */
package com.dailyrental.domain;

import java.time.LocalDateTime;

/**
 * @author Michael Grote, GROTEMI (415)
 */
public class VehicleMovement {
    private LocalDateTime checkout;
    private LocalDateTime checkin;

    public LocalDateTime getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDateTime checkout) {
        this.checkout = checkout;
    }

    public LocalDateTime getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDateTime checkin) {
        this.checkin = checkin;
    }
}
