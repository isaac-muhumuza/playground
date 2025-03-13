package io.sample.playground.patterns.structural.bridge;

public class Triangle extends ShapeWithBridge {

    public Triangle(Colour colour) {
        super(colour);
    }

    @Override
     public String getShape() {
        return "Triangle " + colour.colourFill();
    }
}
