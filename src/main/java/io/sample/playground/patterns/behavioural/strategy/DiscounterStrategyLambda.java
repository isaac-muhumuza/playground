package io.sample.playground.patterns.behavioural.strategy;

import java.math.BigDecimal;

public interface DiscounterStrategyLambda {

    BigDecimal applyDiscount(BigDecimal amount);
    static DiscounterStrategyLambda easterDiscounter() {
        return amount -> amount.multiply(BigDecimal.valueOf(0.5));
    }

    static DiscounterStrategyLambda christmasDiscounter() {
        return amount -> amount.multiply(BigDecimal.valueOf(0.7));
    }
}
