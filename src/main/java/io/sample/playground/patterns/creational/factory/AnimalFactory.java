package io.sample.playground.patterns.creational.factory;

// creator
public abstract class AnimalFactory {

    protected abstract Animal createAnimal(String type);
}
