package io.sample.java_new_changes.features.sealed_classes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Circle implements Shape {

    @Override
    public void printShape() {
        log.info("Printing a circle");
    }
}
