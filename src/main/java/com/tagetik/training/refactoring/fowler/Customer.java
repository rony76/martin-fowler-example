package com.tagetik.training.refactoring.fowler;

import java.util.LinkedList;
import java.util.List;

public class Customer {
    private final PhoneNumber homePhone = new PhoneNumber();
    private final PhoneNumber officePhone = new PhoneNumber();
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
        this.homePhone.prefix = homePrefix;
    }

    public String getHomePrefix() {
        return homePhone.getPrefix();
    }

    public void setHomeNumber(String homeNumber) {
        this.homePhone.setNumber(homeNumber);
    }

    public String getHomeNumber() {
        return homePhone.getNumber();
    }

    public String getOfficePrefix() {
        return officePhone.getPrefix();
    }

    public void setOfficePrefix(String officePrefix) {
        this.officePhone.prefix = officePrefix;
    }

    public String getOfficeNumber() {
        return officePhone.getNumber();
    }

    public void setOfficeNumber(String officeNumber) {
        this.officePhone.setNumber(officeNumber);
    }

    public void dialHome(Dialler dialler) {
        homePhone.dial(dialler);
    }

    public void dialOffice(Dialler dialler) {
        officePhone.dial(dialler);
    }
}
