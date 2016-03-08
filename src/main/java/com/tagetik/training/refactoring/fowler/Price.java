package com.tagetik.training.refactoring.fowler;

public interface Price {
    int getPriceCode();

    double getCharge(int daysRented);

    int getFrequentRenterPoints(int daysRented);
}
