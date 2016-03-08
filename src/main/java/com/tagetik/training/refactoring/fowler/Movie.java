package com.tagetik.training.refactoring.fowler;

public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String title;
    private Price price;

    public Movie(String title, int priceCode) {
        this.title = title;
        setPriceCode(priceCode);
    }

    public int getPriceCode() {
        return price.getPriceCode();
    }

    public void setPriceCode(int priceCode) {
        switch (priceCode) {
            case Movie.CHILDRENS:
                price = new ChildrensPrice();
                break;
            case Movie.REGULAR:
                price = new RegularPrice();
                break;
            case Movie.NEW_RELEASE:
                price = new NewReleasePrice();
                break;
        }
    }

    public String getTitle() {
        return title;
    }

    public double getCharge(int daysRented) {
        switch (getPriceCode()) {
            case REGULAR:
                if (daysRented > 2) {
                    return 2.0 + (daysRented - 2) * 1.5;
                }
                return 2.0;

            case NEW_RELEASE:
                return daysRented * 3.0;

            case CHILDRENS:
                if (daysRented > 3) {
                    return 1.5 + (daysRented - 3) * 1.5;
                }
                return 1.5;
        }

        return 0;
    }

    public int getFrequentRenterPoints(int daysRented) {
        if ((getPriceCode() == NEW_RELEASE) && daysRented > 1) {
            return 2;
        } else {
            return 1;
        }
    }
}
