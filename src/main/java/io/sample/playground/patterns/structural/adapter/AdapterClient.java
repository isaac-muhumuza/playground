package io.sample.playground.patterns.structural.adapter;

public class AdapterClient {

    public static void main(String[] args) {
        Target target = new Adapter(new AdapteeImpl());
        target.print();
    }
}
