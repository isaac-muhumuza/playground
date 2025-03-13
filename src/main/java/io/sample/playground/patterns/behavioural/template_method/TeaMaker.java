package io.sample.playground.patterns.behavioural.template_method;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TeaMaker extends BeverageMaker {

    @Override
    void brew() {
      log.info("Brewing Tea");
    }

    @Override
    void addCondiments() {
        log.info("Adding lemon and milk");
    }
}
