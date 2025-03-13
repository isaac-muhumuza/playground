package io.sample.java_new_changes.features.sealed_classes;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public sealed class Rectangle implements Shape permits Square {

    @Override
    public void printShape() {
        log.info("Printing a rectangle");
    }
}
