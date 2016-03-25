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
        String result = "Rental Record for " + formatter.formatCustomerName(getName()) + "\n";
        for (Rental rental : rentals) {
            // show figures for this rental
            result += "\t" + formatter.formatTitle(rental.getMovie().getTitle()) + "\t" + rental.getCharge() + "\n";
        }

        // add footer lines
        result += "Amount owed is " + formatter.formatCharge(getTotalCharge()) + "\n";
        result += "You earned " + formatter.formatPoints(getFrequentRenterPoints()) + " frequent renter points";

        return result;
    }

    public String markdownStatement() {
        MdFormatter formatter = new MdFormatter();

        String result = "Rental Record for " +
                formatter.formatCustomerName(getName()) +
                "\n";
        for (Rental rental : rentals) {
            // show figures for this rental
            result += "\t" + formatter.formatTitle(rental.getMovie().getTitle()) +
                    "\t" + rental.getCharge() + "\n";
        }

        // add footer lines
        result += "Amount owed is " +
                formatter.formatCharge(getTotalCharge()) +
                "\n";
        result += "You earned " +
                formatter.formatPoints(getFrequentRenterPoints()) +
                " frequent renter points";

        return result;
    }

    private static class PlainFormatter {
        private String formatPoints(int totalFrequentRenterPoints) {
            return Integer.toString(totalFrequentRenterPoints);
        }

        private String formatCharge(double totalCharge) {
            return Double.toString(totalCharge);
        }

        private String formatTitle(String title) {
            return title;
        }

        private String formatCustomerName(String name) {
            return name;
        }
    }

    private static class MdFormatter {
        private String formatPoints(int totalFrequentRenterPoints) {
            return "**" + totalFrequentRenterPoints + "**";
        }

        private String formatCharge(double totalCharge) {
            return "**" + totalCharge + "**";
        }

        private String formatTitle(String title) {
            return "*" + title + "*";
        }

        private String formatCustomerName(String name) {
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
