package com.tagetik.training.refactoring.fowler;

public class RegularPrice implements Price {
    @Override
    public int getPriceCode() {
        return Movie.REGULAR;
    }

    @Override
    public double getCharge(int daysRented) {
        if (daysRented > 2) {
            return 2.0 + (daysRented - 2) * 1.5;
        }
        return 2.0;
    }
}
