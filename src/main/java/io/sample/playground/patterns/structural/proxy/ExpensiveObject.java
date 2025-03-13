package io.sample.playground.patterns.structural.proxy;

// used when we wnat a simplified version ofan expensive object
// when ojbect is present in different address space but we want to provide a local representation of that object
// when we want to control access to an object
public interface ExpensiveObject {

    String process();
}
