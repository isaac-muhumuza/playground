package io.sample.playground.patterns.creational.abstract_factory;

public class Dog implements Animal {
    @Override
    public String getType() {
        return "dog";
    }

    @Override
    public String makeSound() {
        return "barks";
    }
}
