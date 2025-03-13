package io.sample.playground.patterns.creational.abstract_factory;

public class ColourFactory implements AbstractFactory<Colour> {

    @Override
    public Colour create(String colour) {
        return switch (colour) {
            case "black" ->  new Black();
            case "grey" ->  new Grey();
            default ->  null;
        };
    }
}
