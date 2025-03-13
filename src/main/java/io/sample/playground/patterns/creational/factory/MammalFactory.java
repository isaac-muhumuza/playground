package io.sample.playground.patterns.creational.factory;

// concrete creator
public class MammalFactory extends AnimalFactory {
    @Override
    protected Animal createAnimal(String type) {
        return new Mammal();
    }
}
