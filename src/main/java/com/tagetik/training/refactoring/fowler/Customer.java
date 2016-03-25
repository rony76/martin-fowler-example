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
        PlainFormatter formatter = new PlainFormatter();
        String result = "Rental Record for " + formatter.formatPlainName(getName()) + "\n";
        for (Rental rental : rentals) {
            // show figures for this rental
            result += "\t" + formatter.formatPlainTitle(rental.getMovie().getTitle()) + "\t" + rental.getCharge() + "\n";
        }

        // add footer lines
        result += "Amount owed is " + formatter.formatPlainCharge(getTotalCharge()) + "\n";
        result += "You earned " + formatter.formatPlainTotalFrequentRenterPoints(getFrequentRenterPoints()) + " frequent renter points";

        return result;
    }

    public String markdownStatement() {
        MdFormatter formatter = new MdFormatter();

        String result = "Rental Record for " +
                formatter.formatMdName(getName()) +
                "\n";
        for (Rental rental : rentals) {
            // show figures for this rental
            result += "\t" + formatter.formatMdMovieTitle(rental.getMovie().getTitle()) +
                    "\t" + rental.getCharge() + "\n";
        }

        // add footer lines
        result += "Amount owed is " +
                formatter.formatMdTotalCharge(getTotalCharge()) +
                "\n";
        result += "You earned " +
                formatter.formatMdPoints(getFrequentRenterPoints()) +
                " frequent renter points";

        return result;
    }

    private static class PlainFormatter {
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
    }

    private static class MdFormatter {
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
