package com.tagetik.training.refactoring.fowler;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.regex.Pattern;

class TotalAmountMatcher extends BaseMatcher<String> {
    private static final Pattern PATTERN = Pattern.compile("Amount owed is (\\d+(:?\\.\\d+))");
    public static final double EPSILON = .00001;

    private final double expectedTotalAmount;

    public TotalAmountMatcher(double expectedTotalAmount) {
        this.expectedTotalAmount = expectedTotalAmount;
    }

    static Matcher<String> requiresAmount(double amount) {
        return new TotalAmountMatcher(amount);
    }

    @Override
    public boolean matches(Object item) {
        if (!(item instanceof String)) {
            return false;
        }

        String s = ((String) item);

        java.util.regex.Matcher matcher = PATTERN.matcher(s);
        if (!matcher.find()) {
            return false;
        }

        return Math.abs(Double.valueOf(matcher.group(1)) - expectedTotalAmount) < EPSILON;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("A statement stating the customer owes ");
        description.appendValue(expectedTotalAmount);
        description.appendText(" dollar(s)");
    }
}
