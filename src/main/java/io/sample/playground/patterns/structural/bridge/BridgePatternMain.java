package io.sample.playground.patterns.structural.bridge;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BridgePatternMain {

    public static void main(String [] args) {

        ShapeWithBridge triangle = new Triangle(new Red());
        log.info(triangle.getShape());

        ShapeWithBridge square = new Square(new Red());
        log.info(square.getShape());
    }
}
