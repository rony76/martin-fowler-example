package com.tagetik.training.refactoring.fowler;

public class RegularPrice implements Price {
    @Override
    public int getPriceCode() {
        return Movie.REGULAR;
    }
}
