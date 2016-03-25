package com.tagetik.training.refactoring.fowler;

import java.util.LinkedList;
import java.util.List;

public class Customer {
    private String name;
    private List<Rental> rentals = new LinkedList<Rental>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        String result = "Rental Record for " + formatPlainName(getName()) + "\n";
        for (Rental rental : rentals) {
            // show figures for this rental
            result += "\t" + formatPlainTitle(rental.getMovie().getTitle()) + "\t" + rental.getCharge() + "\n";
        }

        // add footer lines
        result += "Amount owed is " + formatPlainCharge(getTotalCharge()) + "\n";
        result += "You earned " + formatPlainTotalFrequentRenterPoints(getFrequentRenterPoints()) + " frequent renter points";

        return result;
    }

    public String markdownStatement() {
        String result = "Rental Record for " +
                formatMdName(getName()) +
                "\n";
        for (Rental rental : rentals) {
            // show figures for this rental
            result += "\t" + formatMdMovieTitle(rental.getMovie().getTitle()) +
                    "\t" + rental.getCharge() + "\n";
        }

        // add footer lines
        result += "Amount owed is " +
                formatMdTotalCharge(getTotalCharge()) +
                "\n";
        result += "You earned " +
                formatMdPoints(getFrequentRenterPoints()) +
                " frequent renter points";

        return result;
    }

    private String formatPlainTotalFrequentRenterPoints(int totalFrequentRenterPoints) {
        return Integer.toString(totalFrequentRenterPoints);
    }

    private String formatPlainCharge(double totalCharge) {
        return Double.toString(totalCharge);
    }

    private String formatPlainTitle(String title) {
        return title;
    }

    private String formatPlainName(String name) {
        return name;
    }

    private String formatMdPoints(int totalFrequentRenterPoints) {
        return "**" + totalFrequentRenterPoints + "**";
    }

    private String formatMdTotalCharge(double totalCharge) {
        return "**" + totalCharge + "**";
    }

    private String formatMdMovieTitle(String title) {
        return "*" + title + "*";
    }

    private String formatMdName(String name) {
        return "**" + name + "**";
    }

    private int getFrequentRenterPoints() {
        int result = 0;
        for (Rental rental : rentals) {
            result += rental.getFrequentRenterPoints();
        }
        return result;
    }

    private double getTotalCharge() {
        double result = 0;
        for (Rental rental : rentals) {
            result += rental.getCharge();
        }
        return result;
    }

}
