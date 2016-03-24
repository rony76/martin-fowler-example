package com.tagetik.training.refactoring.fowler;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.regex.Pattern;

class MarkdownPointsMatcher extends TypeSafeMatcher<String> {
    private static final Pattern PATTERN = Pattern.compile("You earned \\*\\*(\\d++)\\*\\* frequent renter points");

    private final int expectedPoints;

    private MarkdownPointsMatcher(int expectedPoints) {
        this.expectedPoints = expectedPoints;
    }

    static Matcher<String> grantsPointsInMarkdown(int expectedPoints) {
        return new MarkdownPointsMatcher(expectedPoints);
    }

    @Override
    protected boolean matchesSafely(String item) {
        java.util.regex.Matcher matcher = PATTERN.matcher(item);
        if (!matcher.find()) {
            return false;
        }

        return Integer.valueOf(matcher.group(1)) == expectedPoints;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("A Markdown statement stating the customer earned ");
        description.appendValue(expectedPoints);
        description.appendText(" point(s)");
    }
}
