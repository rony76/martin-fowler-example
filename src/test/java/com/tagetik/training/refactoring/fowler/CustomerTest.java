package com.tagetik.training.refactoring.fowler;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.tagetik.training.refactoring.fowler.MarkdownPointsMatcher.grantsPointsInMarkdown;
import static com.tagetik.training.refactoring.fowler.MarkdownTotalAmountMatcher.requiresAmountInMarkdown;
import static com.tagetik.training.refactoring.fowler.PointsMatcher.grantsPoints;
import static com.tagetik.training.refactoring.fowler.TotalAmountMatcher.requiresAmount;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class CustomerTest {

    private static final Movie NEW_RELEASED_MOVIE = new Movie("A brand new film", Movie.NEW_RELEASE);
    private static final Movie CHILDREN_MOVIE = new Movie("Shaun the sheep", Movie.CHILDRENS);
    private static final Movie REGULAR_MOVIE = new Movie("Young Frankenstein", Movie.REGULAR);
    private static final Movie THREE_D_MOVIE = new Movie("Alice in Wonderland", Movie.THREE_D);

    @Test
    public void noRentalImpliesNoCharge() throws Exception {
        Customer customer = createCustomer();

        assertThat(customer.statement(), requiresAmount(0));
        assertThat(customer.markdownStatement(), requiresAmountInMarkdown(0));
    }

    @Test
    public void noRentalGrantsNoPoints() throws Exception {
        Customer customer = createCustomer();

        assertThat(customer.statement(), grantsPoints(0));
        assertThat(customer.markdownStatement(), grantsPointsInMarkdown(0));
    }

    @Test
    public void newReleaseForOneDayCostsThreeDollars() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(NEW_RELEASED_MOVIE, 1));

        assertThat(customer.statement(), requiresAmount(3.0));
        assertThat(customer.markdownStatement(), requiresAmountInMarkdown(3.0));
    }

    @Test
    public void newReleaseForTwoDaysCostsThreeDollarsADay() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(NEW_RELEASED_MOVIE, 2));

        assertThat(customer.statement(), requiresAmount(6.0));
        assertThat(customer.markdownStatement(), requiresAmountInMarkdown(6.0));
    }

    @Test
    public void newReleaseForTwoDaysGrantsExtraPoints() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(NEW_RELEASED_MOVIE, 2));

        assertThat(customer.statement(), grantsPoints(2));
        assertThat(customer.markdownStatement(), grantsPointsInMarkdown(2));
    }

    @Test
    public void childrensMovieForOneDayCostsOneAndAHalfDollars() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(CHILDREN_MOVIE, 1));

        assertThat(customer.statement(), requiresAmount(1.5));
        assertThat(customer.markdownStatement(), requiresAmountInMarkdown(1.5));
    }

    @Test
    public void childrensMovieCostsExtraPastThirdDay() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(CHILDREN_MOVIE, 5));

        assertThat(customer.statement(), requiresAmount(1.5 + 2 * 1.5));
        assertThat(customer.markdownStatement(), requiresAmountInMarkdown(1.5 + 2 * 1.5));
    }

    @Test
    public void childrensMovieForTwoDaysStillCostsOneAndAHalfDollars() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(CHILDREN_MOVIE, 2));

        assertThat(customer.statement(), requiresAmount(1.5));
        assertThat(customer.markdownStatement(), requiresAmountInMarkdown(1.5));
    }

    @Test
    public void regularMovieForOneDayCostsTwoDollars() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(REGULAR_MOVIE, 1));

        assertThat(customer.statement(), requiresAmount(2));
        assertThat(customer.markdownStatement(), requiresAmountInMarkdown(2));
    }

    @Test
    public void regularMovieCostsOneAndAHalfExtraDollarsPastSecondDay() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(REGULAR_MOVIE, 4));

        assertThat(customer.statement(), requiresAmount(2 + 2 * 1.5));
        assertThat(customer.markdownStatement(), requiresAmountInMarkdown(2 + 2 * 1.5));
    }

    @Test
    public void regularMovieForTwoDaysStillCostsTwoDollars() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(REGULAR_MOVIE, 2));

        assertThat(customer.statement(), requiresAmount(2.0));
        assertThat(customer.markdownStatement(), requiresAmountInMarkdown(2.0));
    }

    @Test
    public void movieTitlesAreAllIncludedInStatement() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(REGULAR_MOVIE, 2));
        customer.addRental(new Rental(NEW_RELEASED_MOVIE, 1));

        String twoMoviesStatement = customer.statement();

        assertThat(twoMoviesStatement, containsString(REGULAR_MOVIE.getTitle()));
        assertThat(twoMoviesStatement, containsString(NEW_RELEASED_MOVIE.getTitle()));
    }

    @Test
    public void movieTitlesAreAllIncludedInMarkdownStatement() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(REGULAR_MOVIE, 2));
        customer.addRental(new Rental(NEW_RELEASED_MOVIE, 1));

        String twoMoviesStatement = customer.markdownStatement();

        assertThat(twoMoviesStatement, containsString(REGULAR_MOVIE.getTitle()));
        assertThat(twoMoviesStatement, containsString(NEW_RELEASED_MOVIE.getTitle()));
    }

    @Test
    public void markdownStatementIsAsExpected() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(REGULAR_MOVIE, 2));
        customer.addRental(new Rental(NEW_RELEASED_MOVIE, 1));

        String twoMoviesStatement = customer.markdownStatement();
        String expectedMarkdownStatement = loadExpectedMarkdownStatement();

        assertThat(twoMoviesStatement, equalTo(expectedMarkdownStatement));
    }

    private String loadExpectedMarkdownStatement() throws IOException {
        ClassLoader classLoader = CustomerTest.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("expected-statement.md");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int readVal = inputStream.read();
        while (readVal != -1) {
            if (readVal != '\r') {
                baos.write(readVal);
            }
            readVal = inputStream.read();
        }
        return baos.toString();
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

    @Test
    public void threeDMoviesCostThreeFiftyOnTheFirstDay() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(THREE_D_MOVIE, 1));

        assertThat(customer.statement(), requiresAmount(3.5));
        assertThat(customer.markdownStatement(), requiresAmountInMarkdown(3.5));
    }

    @Test
    public void threeDMoviesCostThreeFiftyOnTheFirstTwoDays() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(THREE_D_MOVIE, 2));

        assertThat(customer.statement(), requiresAmount(3.5));
        assertThat(customer.markdownStatement(), requiresAmountInMarkdown(3.5));
    }

    @Test
    public void threeDMoviesCostFourDollarsEveryThirdDay() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(THREE_D_MOVIE, 10));

        assertThat(customer.statement(), requiresAmount(3.5 + 4.0 * 3));
        assertThat(customer.markdownStatement(), requiresAmountInMarkdown(3.5 + 4.0 * 3));
    }

    @Test
    public void threeDMoviesGrantTwoFRPOnTheFirstDay() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(THREE_D_MOVIE, 1));

        assertThat(customer.statement(), grantsPoints(2));
        assertThat(customer.markdownStatement(), grantsPointsInMarkdown(2));
    }

    @Test
    public void threeDMoviesGrantOneMoreFRPOnEveryThirdDay() throws Exception {
        Customer customer = createCustomer();
        customer.addRental(new Rental(THREE_D_MOVIE, 6));

        assertThat(customer.statement(), grantsPoints(2 + 2));
        assertThat(customer.markdownStatement(), grantsPointsInMarkdown(2 + 2));
    }

    private Customer createCustomer() {
        return new Customer("Fufaffio Fufaffi");
    }

}