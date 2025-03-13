package io.sample.playground.patterns.creational.abstract_factory;

public interface AbstractFactory<T> {

    T create(String type);

}
