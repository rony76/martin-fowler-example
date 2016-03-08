package com.tagetik.training.refactoring.fowler;

public class NewReleasePrice implements Price {
    @Override
    public int getPriceCode() {
        return Movie.NEW_RELEASE;
    }
}
