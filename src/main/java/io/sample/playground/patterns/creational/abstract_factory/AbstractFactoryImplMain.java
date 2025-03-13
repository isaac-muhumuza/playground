package io.sample.playground.patterns.creational.abstract_factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AbstractFactoryImplMain {

    public static void main (String[] args) {
        AbstractFactory abstractDogFactory = FactoryProvider.getFactory("ANIMAL");

        Animal animal = (Animal) abstractDogFactory.create("dog");

        AbstractFactory abstractColourFactory = FactoryProvider.getFactory("Colour");
        Colour colour = (Colour) abstractColourFactory.create("grey");

        log.info("An animal of type " + animal.getType() + " makes sound " + animal.makeSound());
        log.info("A colour of type " + colour.getColour());
    }
}
