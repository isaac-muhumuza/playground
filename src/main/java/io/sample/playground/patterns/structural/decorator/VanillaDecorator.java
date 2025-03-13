package io.sample.playground.patterns.structural.decorator;

public class VanillaDecorator extends CoffeeDecorator {

    public VanillaDecorator(Coffee decoratorCoffee) {
        super(decoratorCoffee);
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " with vanilla";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.50;
    }

}
