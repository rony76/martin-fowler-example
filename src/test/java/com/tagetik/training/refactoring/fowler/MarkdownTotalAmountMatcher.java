package com.tagetik.training.refactoring.fowler;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.regex.Pattern;

class MarkdownTotalAmountMatcher extends TypeSafeMatcher<String> {
    private static final Pattern PATTERN = Pattern.compile("Amount owed is \\*\\*(\\d+(:?\\.\\d+))\\*\\*");
    private static final double EPSILON = .00001;

    private final double expectedTotalAmount;

    private MarkdownTotalAmountMatcher(double expectedTotalAmount) {
        this.expectedTotalAmount = expectedTotalAmount;
    }

    static Matcher<String> requiresAmountInMarkdown(double amount) {
        return new MarkdownTotalAmountMatcher(amount);
    }

    @Override
    protected boolean matchesSafely(String item) {
        java.util.regex.Matcher matcher = PATTERN.matcher(item);
        if (!matcher.find()) {
            return false;
        }

        return Math.abs(Double.valueOf(matcher.group(1)) - expectedTotalAmount) < EPSILON;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("A Markdown statement stating the customer owes ");
        description.appendValue(expectedTotalAmount);
        description.appendText(" dollar(s)");
    }
}
