package io.sample.playground.patterns.creational.abstract_factory;

public class Cat implements Animal {
    @Override
    public String getType() {
        return "cat";
    }

    @Override
    public String makeSound() {
        return "meows";
    }
}
