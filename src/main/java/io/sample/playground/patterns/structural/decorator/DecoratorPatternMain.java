package io.sample.playground.patterns.structural.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecoratorPatternMain {

    public static void main (String [] args) {
        Coffee espresso = new Espresso();
        log.info("Coffee details : " + espresso.getDetails() + " cost - " + espresso.getCost());

        Coffee decoratedCOffee = new MilkDecorator(new Espresso());
        log.info("Decorated Coffee details: " + decoratedCOffee.getDetails() + " cost - " + decoratedCOffee.getCost());

        Coffee highlyDecoratedCoffee = new VanillaDecorator(new MilkDecorator(new Americano()));
        log.info("Highly decorated Coffee details : " + highlyDecoratedCoffee.getDetails() + " cost - " + highlyDecoratedCoffee.getCost());
    }
}
