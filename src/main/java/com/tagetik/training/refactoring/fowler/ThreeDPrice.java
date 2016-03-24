package com.tagetik.training.refactoring.fowler;

public class ThreeDPrice implements Price {
    @Override
    public int getPriceCode() {
        return Movie.THREE_D;
    }

    @Override
    public double getCharge(int daysRented) {
        return 3.5 + daysRented / 3 * 4;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        return 2 + daysRented / 3;
    }
}
