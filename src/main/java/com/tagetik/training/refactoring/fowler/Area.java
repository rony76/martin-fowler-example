package com.tagetik.training.refactoring.fowler;

public enum Area {
    AMERICA, ASIA, OCEANIA, AFRICA, EUROPE, ANTARTICA;

    public boolean isInEurope() {
        return this == EUROPE;
    }
}
