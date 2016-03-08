package com.tagetik.training.refactoring.fowler;

public class ChildrensPrice implements Price {
    @Override
    public int getPriceCode() {
        return Movie.CHILDRENS;
    }

    @Override
    public double getCharge(int daysRented) {
        if (daysRented > 3) {
            return 1.5 + (daysRented - 3) * 1.5;
        }
        return 1.5;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
