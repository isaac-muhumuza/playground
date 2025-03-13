package io.sample.playground.patterns.behavioural.strategy;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class StrategyDiscounterMain {

    public static void main(String[] args) {
        //using inherited classes
        EasterDiscounter easterDiscounter = new EasterDiscounter();
        log.info("Easter Discount from inheritance is: {}", easterDiscounter.applyDiscount(BigDecimal.valueOf(500)));
        ChristmasDiscounter christmasDiscounter = new ChristmasDiscounter();
        log.info("Christmas Discount from inheritance is: {}", christmasDiscounter.applyDiscount(BigDecimal.valueOf(500)));


        //using lambda
        DiscounterStrategyLambda easterLambda = DiscounterStrategyLambda.easterDiscounter();
        log.info("Easter Discount from lambda is: {}", easterLambda.applyDiscount(BigDecimal.valueOf(500)));

        DiscounterStrategyLambda christmasDiscountLambda = DiscounterStrategyLambda.christmasDiscounter();
        log.info("Christmas Discount from lambda is: {}", christmasDiscountLambda.applyDiscount(BigDecimal.valueOf(500)));
    }
}
