package com.tagetik.training.refactoring.fowler;

public class Rental {
    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public double getCharge() {
        switch (getMovie().getPriceCode()) {
            case Movie.REGULAR:
                if (getDaysRented() > 2) {
                    return 2.0 + (getDaysRented() - 2) * 1.5;
                }
                return 2.0;

            case Movie.NEW_RELEASE:
                return getDaysRented() * 3.0;

            case Movie.CHILDRENS:
                if (getDaysRented() > 3) {
                    return 1.5 + (getDaysRented() - 3) * 1.5;
                }
                return 1.5;
        }

        return 0;
    }

    public int getFrequentRenterPoints() {
        if ((getMovie().getPriceCode() == Movie.NEW_RELEASE) && getDaysRented() > 1) {
            return 2;
        } else {
            return 1;
        }

    }
}
