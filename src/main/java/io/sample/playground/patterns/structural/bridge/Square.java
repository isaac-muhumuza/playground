package io.sample.playground.patterns.structural.bridge;

public class Square extends ShapeWithBridge {


    public Square(Colour colour) {
        super(colour);
    }

    @Override
    public String getShape() {
        return "Square " + colour.colourFill();
    }
}
