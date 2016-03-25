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
        return formattedStatement(new PlainFormatter());
    }

    public String markdownStatement() {
        return formattedStatement(new MdFormatter());
    }

    private String formattedStatement(StatementFormatter formatter) {
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

    private static class PlainFormatter implements StatementFormatter {
        @Override
        public String formatPoints(int totalFrequentRenterPoints) {
            return Integer.toString(totalFrequentRenterPoints);
        }

        @Override
        public String formatCharge(double totalCharge) {
            return Double.toString(totalCharge);
        }

        @Override
        public String formatTitle(String title) {
            return title;
        }

        @Override
        public String formatCustomerName(String name) {
            return name;
        }
    }

    private interface StatementFormatter {
        String formatPoints(int totalFrequentRenterPoints);

        String formatCharge(double totalCharge);

        String formatTitle(String title);

        String formatCustomerName(String name);
    }


    private static class MdFormatter implements StatementFormatter {
        @Override
        public String formatPoints(int totalFrequentRenterPoints) {
            return "**" + totalFrequentRenterPoints + "**";
        }

        @Override
        public String formatCharge(double totalCharge) {
            return "**" + totalCharge + "**";
        }

        @Override
        public String formatTitle(String title) {
            return "*" + title + "*";
        }

        @Override
        public String formatCustomerName(String name) {
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
