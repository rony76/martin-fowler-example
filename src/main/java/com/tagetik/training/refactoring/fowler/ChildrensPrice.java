package com.tagetik.training.refactoring.fowler;

public class ChildrensPrice implements Price {
    @Override
    public int getPriceCode() {
        return Movie.CHILDRENS;
    }
}
