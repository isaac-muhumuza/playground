package io.sample.playground.patterns.behavioural.template_method;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CoffeeMaker extends BeverageMaker {


    @Override
    void brew() {
        log.info("Brewing coffee");
    }

    @Override
    void addCondiments() {
        log.info("Adding sugar, cinnamon and milk");
    }
}
