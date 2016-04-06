package com.tagetik.training.refactoring.fowler;

import java.util.LinkedList;
import java.util.List;

public class Customer {
    private String name;
    private String homePrefix;
    private String homeNumber;
    private String officePrefix;
    private String officeNumber;

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

        String result = "Rental Record for " + getName() + "\n";
        for (Rental rental : rentals) {
            // show figures for this rental
            result += "\t" + rental.getMovie().getTitle() + "\t" + rental.getCharge() + "\n";
        }

        // add footer lines
        result += "Amount owed is " + getTotalCharge() + "\n";
        result += "You earned " + getFrequentRenterPoints() + " frequent renter points";

        return result;
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

    public void setHomePrefix(String homePrefix) {
        this.homePrefix = homePrefix;
    }

    public String getHomePrefix() {
        return homePrefix;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public String getOfficePrefix() {
        return officePrefix;
    }

    public void setOfficePrefix(String officePrefix) {
        this.officePrefix = officePrefix;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public void dialHome(Dialler dialler) {
        dialler.dial(homePrefix + homeNumber);
    }

    public void dialOffice(Dialler dialler) {
        dialler.dial(officePrefix + officeNumber);
    }
}
