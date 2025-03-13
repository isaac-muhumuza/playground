package io.sample.playground.patterns.creational.factory;

// concrete product
public class Mammal implements Animal {
    @Override
    public String getType() {
        return "Mammal";
    }
}
