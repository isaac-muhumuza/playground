package io.sample.playground.patterns.structural.decorator;

public class Espresso implements Coffee {
    @Override
    public String getDetails() {
        return"Espresso coffee";
    }

    @Override
    public double getCost() {
        return 1.50;
    }
}
