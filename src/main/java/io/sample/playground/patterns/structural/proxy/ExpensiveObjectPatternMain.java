package io.sample.playground.patterns.structural.proxy;

public class ExpensiveObjectPatternMain {

    public static void main(String[] args) {
        ExpensiveObject expensiveObject = new ExpensiveObjectProxy();

        expensiveObject.process();
        expensiveObject.process();
        expensiveObject.process();
    }
}
