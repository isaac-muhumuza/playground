package io.sample.playground.patterns.behavioural.template_method;

public class TemplateMethodPatternMain {

    public static void main(String[] args) {
        BeverageMaker teamaker = new TeaMaker();
        teamaker.makeBeverage();

        BeverageMaker coffeeMaker = new CoffeeMaker();
        coffeeMaker.makeBeverage();
    }
}
