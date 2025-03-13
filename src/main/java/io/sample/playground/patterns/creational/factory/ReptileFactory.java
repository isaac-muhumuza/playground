package io.sample.playground.patterns.creational.factory;

public class ReptileFactory extends AnimalFactory {
    @Override
    protected Animal createAnimal(String type) {
        return new Reptile();
    }
}
