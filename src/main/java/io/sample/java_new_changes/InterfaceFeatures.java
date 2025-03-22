package io.sample.java_new_changes;

// private methods inside interfaces to use within default methods.
public interface InterfaceFeatures {
    private String getString() {
        return "Java 9 feature: private methods inside interfaces.";
    }

    default String printString() {
        return getString();
    }

    default int multiply(int a, int b) {
        return a * b;
    }
}
