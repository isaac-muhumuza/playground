package io.sample.java_new_changes.features.sealed_classes;

public sealed interface Shape permits Circle, Rectangle {

    void printShape();
}
