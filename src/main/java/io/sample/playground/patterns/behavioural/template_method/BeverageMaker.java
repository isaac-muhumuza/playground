package io.sample.playground.patterns.behavioural.template_method;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BeverageMaker {

    public final void makeBeverage() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }
    abstract void brew();
    abstract void addCondiments();

     private void boilWater() {
         log.info("Boiling water");
     }

     private void pourInCup() {
         log.info("Pouring into cup");
     }
}
