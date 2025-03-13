package io.sample.playground.patterns.creational.abstract_factory;

public class AnimalFactory implements AbstractFactory<Animal>{
    @Override
    public Animal create(String type) {
        return switch (type) {
            case "dog" -> new Dog();
            case "cat" -> new Cat();
            default -> null;
        };
    }
}
