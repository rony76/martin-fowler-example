package com.tagetik.training.refactoring.fowler;

public class Country {
    private final Area area;

    public Country(Area area) {
        this.area = area;
    }

    public Area getArea() {
        return area;
    }
}
