package com.android.challenge.yourself.be.constants;

public enum Measurement {
    STEPS("Steps"),
    PAGES("Pages"),
    MINUTES("Minutes"),
    HOURS("Hours"),
    LITERS("Liters"),
    CUPS("Cups"),
    ITERATIONS("Iterations");

    private String displayValue;

    Measurement(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
