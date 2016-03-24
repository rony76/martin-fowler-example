package com.tagetik.training.refactoring.fowler;

import org.junit.Test;

import static com.tagetik.training.refactoring.fowler.PointsMatcher.grantsPoints;
import static com.tagetik.training.refactoring.fowler.TotalAmountMatcher.requiresAmount;
import static org.junit.Assert.assertThat;

public class CustomerTest {

    private static final Movie NEW_RELEASED_MOVIE = new Movie("A brand new film", Movie.NEW_RELEASE);
    private static final Movie CHILDREN_MOVIE = new Movie("Shaun the sheep", Movie.CHILDRENS);
    private static final Movie REGULAR_MOVIE = new Movie("Young Frankenstein", Movie.REGULAR);

    @Test
    public void noRentalImpliesNoCharge() throws Exception {
        Customer customer = createCustomer();
        String emptyStatement = customer.statement();

        assertThat(emptyStatement, requiresAmount(0));
    }

    @Test
    public void noRentalGrantsNoPoints() throws Exception {
        Customer customer = createCustomer();
        String emptyStatement = customer.statement();

        assertThat(emptyStatement, grantsPoints(0));
    }

    @Test
    public void newReleaseForOneDayCostsThreeDollars() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(NEW_RELEASED_MOVIE, 1));

        String twoDaysStatement = customer.statement();

        assertThat(twoDaysStatement, requiresAmount(3.0));
    }

    @Test
    public void newReleaseForTwoDaysCostsThreeDollarsADay() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(NEW_RELEASED_MOVIE, 2));

        String twoDaysStatement = customer.statement();

        assertThat(twoDaysStatement, requiresAmount(6.0));
    }

    @Test
    public void newReleaseForTwoDaysGrantsExtraPoints() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(NEW_RELEASED_MOVIE, 2));

        String twoDaysStatement = customer.statement();

        assertThat(twoDaysStatement, grantsPoints(2));
    }

    @Test
    public void childrensMovieForOneDayCostsOneAndAHalfDollars() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(CHILDREN_MOVIE, 1));

        String twoDaysStatement = customer.statement();

        assertThat(twoDaysStatement, requiresAmount(1.5));
    }

    @Test
    public void childrensMovieCostsExtraPastThirdDay() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(CHILDREN_MOVIE, 5));

        String twoDaysStatement = customer.statement();

        assertThat(twoDaysStatement, requiresAmount(1.5 + 2 * 1.5));
    }

    @Test
    public void childrensMovieForTwoDaysStillCostsOneAndAHalfDollars() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(CHILDREN_MOVIE, 2));

        String twoDaysStatement = customer.statement();

        assertThat(twoDaysStatement, requiresAmount(1.5));
    }

    @Test
    public void regularMovieForOneDayCostsTwoDollars() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(REGULAR_MOVIE, 1));

        String twoDaysStatement = customer.statement();

        assertThat(twoDaysStatement, requiresAmount(2));
    }

    @Test
    public void regularMovieCostsOneAndAHalfExtraDollarsPastSecondDay() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(REGULAR_MOVIE, 4));

        String twoDaysStatement = customer.statement();

        assertThat(twoDaysStatement, requiresAmount(2 + 2 * 1.5));
    }

    @Test
    public void regularMovieForTwoDaysStillCostsTwoDollars() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(REGULAR_MOVIE, 2));

        String twoDaysStatement = customer.statement();

        assertThat(twoDaysStatement, requiresAmount(2.0));
    }

    @Test
    public void amountsForDifferentMoviesAreAccumulated() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(REGULAR_MOVIE, 1));
        customer.addRental(new Rental(CHILDREN_MOVIE, 2));

        String twoDaysStatement = customer.statement();

        assertThat(twoDaysStatement, requiresAmount(2.0 + 1.5));
    }

    @Test
    public void frequentRenterPointsForDifferentMoviesAreAccumulated() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(REGULAR_MOVIE, 1));
        customer.addRental(new Rental(CHILDREN_MOVIE, 2));

        String twoDaysStatement = customer.statement();

        assertThat(twoDaysStatement, grantsPoints(1 + 1));
    }

    private Customer createCustomer() {
        return new Customer("Fufaffio Fufaffi");
    }

}