package com.tagetik.training.refactoring.fowler;

public class PhoneNumber {
    String prefix;
    private String number;

    public PhoneNumber() {
    }

    public String getPrefix() {
        return prefix;
    }

    public String getNumber() {
        return number;
    }

    public void dial(Dialler dialler) {
        dialler.dial(prefix + number);
    }

    public void setNumber(String number) {
        this.number = number;
    }
}