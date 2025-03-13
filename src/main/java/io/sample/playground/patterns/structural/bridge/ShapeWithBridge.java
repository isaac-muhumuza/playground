package io.sample.playground.patterns.structural.bridge;

public abstract class ShapeWithBridge {
    protected Colour colour;

    protected ShapeWithBridge(Colour colour) {
        this.colour = colour;
    }

    public abstract String getShape();
}
