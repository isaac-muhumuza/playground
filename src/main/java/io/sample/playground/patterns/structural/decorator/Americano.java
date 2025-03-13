package io.sample.playground.patterns.structural.decorator;

public class Americano implements Coffee {
    @Override
    public String getDetails() {
        return "Americano/Black coffee";
    }

    @Override
    public double getCost() {
        return 3.0;
    }
}
