package io.sample.playground.patterns.behavioural.strategy;

import java.math.BigDecimal;

public class ChristmasDiscounter implements DiscounterStrategy {

    @Override
    public BigDecimal applyDiscount(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(0.7));
    }
}
