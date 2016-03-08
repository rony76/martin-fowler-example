package com.tagetik.training.refactoring.fowler;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.regex.Pattern;

class PointsMatcher extends BaseMatcher<String> {
    private static final Pattern PATTERN = Pattern.compile("You earned (\\d+) frequent renter points");

    private final int expectedPoints;

    public PointsMatcher(int expectedPoints) {
        this.expectedPoints = expectedPoints;
    }

    static Matcher<String> grantsPoints(int expectedPoints) {
        return new PointsMatcher(expectedPoints);
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

        return Integer.valueOf(matcher.group(1)) == expectedPoints;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("A statement stating the customer earned ");
        description.appendValue(expectedPoints);
        description.appendText(" point(s)");
    }
}
