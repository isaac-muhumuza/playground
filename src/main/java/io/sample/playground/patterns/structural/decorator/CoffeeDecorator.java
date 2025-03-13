package io.sample.playground.patterns.structural.decorator;

public abstract class CoffeeDecorator implements Coffee {

    private Coffee decoratorCoffee;

    protected CoffeeDecorator(Coffee decoratorCoffee) {
        this.decoratorCoffee = decoratorCoffee;
    }

    @Override
    public String getDetails() {
        return decoratorCoffee.getDetails();
    }

    @Override
    public double getCost() {
        return decoratorCoffee.getCost();
    }
}
