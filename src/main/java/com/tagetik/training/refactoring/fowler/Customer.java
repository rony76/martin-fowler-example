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
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";

        for (Rental rental : rentals) {
            frequentRenterPoints += rental.getFrequentRenterPoints();

            // show figures for this rental
            result += "\t" + rental.getMovie().getTitle() + "\t" + rental.getCharge() + "\n";
            totalAmount += rental.getCharge();
        }

        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";

        return result;
    }

}
