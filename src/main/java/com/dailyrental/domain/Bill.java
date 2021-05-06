/*
 * File: Bill
 * Created on 09.09.2020
 *
 * Copyright (c) 2020 by Daimler AG
 */
package com.dailyrental.domain;

/**
 * @author Michael Grote, GROTEMI (415)
 */
public class Bill {
    private int hoursToPay;
    private Customer customer;

    public void addHours(int hoursToPay) {
        this.hoursToPay =+ hoursToPay;
    }

    public int getHoursToPay() {
        return hoursToPay;
    }

    public void setHoursToPay(int hoursToPay) {
        this.hoursToPay = hoursToPay;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
