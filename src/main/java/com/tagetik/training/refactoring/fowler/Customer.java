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
            double thisAmount = 0;

            // determine amounts for each line
            switch (rental.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (rental.getDaysRented() > 2) {
                        thisAmount += (rental.getDaysRented() - 2) * 1.5;
                    }
                    break;

                case Movie.NEW_RELEASE:
                    thisAmount += rental.getDaysRented() * 3;
                    break;

                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (rental.getDaysRented() > 3) {
                        thisAmount += (rental.getDaysRented() - 3) * 1.5;
                    }
                    break;

                case Movie.THREE_D:
                    thisAmount += 3.5;
                    if (rental.getDaysRented() >= 3) {
                        thisAmount += rental.getDaysRented() / 3 * 4.0;
                    }
                    break;

            }

            // add frequent renter points
            frequentRenterPoints++;

            // add bonus for a two day new release rental
            if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1) {
                frequentRenterPoints++;
            }

            // calculate 2 points + 3 points every 3 days for 3D movies
            else if (rental.getMovie().getPriceCode() == Movie.THREE_D) {
                frequentRenterPoints++;
                if (rental.getDaysRented() > 3) {
                    frequentRenterPoints += rental.getDaysRented() / 3;
                }
            }

            // show figures for this rental
            result += "\t" + rental.getMovie().getTitle() + "\t" + thisAmount + "\n";
            totalAmount += thisAmount;
        }

        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";

        return result;
    }

    public String markdownStatement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for **" + getName() + "**\n";

        for (Rental rental : rentals) {
            double thisAmount = 0;

            // determine amounts for each line
            switch (rental.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (rental.getDaysRented() > 2) {
                        thisAmount += (rental.getDaysRented() - 2) * 1.5;
                    }
                    break;

                case Movie.NEW_RELEASE:
                    thisAmount += rental.getDaysRented() * 3;
                    break;

                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (rental.getDaysRented() > 3) {
                        thisAmount += (rental.getDaysRented() - 3) * 1.5;
                    }
                    break;

                case Movie.THREE_D:
                    thisAmount += 3.5;
                    if (rental.getDaysRented() >= 3) {
                        thisAmount += rental.getDaysRented() / 3 * 4.0;
                    }
                    break;
            }

            // add frequent renter points
            frequentRenterPoints++;

            // add bonus for a two day new release rental
            if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1) {
                frequentRenterPoints++;
            }

            // calculate 2 points + 3 points every 3 days for 3D movies
            else if (rental.getMovie().getPriceCode() == Movie.THREE_D) {
                frequentRenterPoints++;
                if (rental.getDaysRented() > 3) {
                    frequentRenterPoints += rental.getDaysRented() / 3;
                }
            }

            // show figures for this rental
            result += "\t*" + rental.getMovie().getTitle() + "*\t" + thisAmount + "\n";
            totalAmount += thisAmount;
        }

        // add footer lines
        result += "Amount owed is **" + totalAmount + "**\n";
        result += "You earned **" + frequentRenterPoints + "** frequent renter points";

        return result;
    }
}
