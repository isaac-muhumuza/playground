package io.sample.playground.patterns.structural.proxy;

public class ExpensiveObjectProxy implements ExpensiveObject {

    private ExpensiveObject expensiveObject;


    @Override
    public String process() {
        if (expensiveObject == null) {
            expensiveObject = new ExpensiveObjectImpl();
        }
        return expensiveObject.process();
    }
}
