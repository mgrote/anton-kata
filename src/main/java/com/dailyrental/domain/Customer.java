/*
 * File: Customer
 * Created on 09.09.2020
 *
 * Copyright (c) 2020 by Daimler AG
 */
package com.dailyrental.domain;

import java.util.Objects;

/**
 * @author Michael Grote, GROTEMI (415)
 */
public class Customer {
    private String name;
    private CustomerType type;
    private boolean isFav;

    public Customer(String name, CustomerType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) &&
                type == customer.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }
}
