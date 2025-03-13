package io.sample.playground.patterns.structural.proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExpensiveObjectImpl implements ExpensiveObject {

    public ExpensiveObjectImpl() {
        log.info("Creating expensive object");
    }

    @Override
    public String process() {
        log.info("Processing object");
        return "Processing object";
    }
}
