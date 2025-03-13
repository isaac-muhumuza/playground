package io.sample.playground.patterns.structural.decorator;

public class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee decoratorCoffee) {
        super(decoratorCoffee);
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " with milk";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.90;
    }

}
