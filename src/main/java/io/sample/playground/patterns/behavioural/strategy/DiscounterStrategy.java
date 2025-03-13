package io.sample.playground.patterns.behavioural.strategy;

import java.math.BigDecimal;

public interface DiscounterStrategy {

    BigDecimal applyDiscount(BigDecimal amount);
}
