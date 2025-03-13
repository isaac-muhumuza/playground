package io.sample.playground.patterns.creational.abstract_factory;

public class FactoryProvider {

    private FactoryProvider() {
    }

    public static AbstractFactory getFactory(String choice) {
        if ("Animal".equalsIgnoreCase(choice)) {
            return new AnimalFactory();
        } else if ("Colour".equalsIgnoreCase(choice)) {
            return new ColourFactory();
        }
        return null;
    }
}
