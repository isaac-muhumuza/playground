package io.sample.playground.patterns.structural.adapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdapteeImpl implements Adaptee {
    @Override
    public void printDocument() {
        log.info("Printing document the old way...");
    }
}
