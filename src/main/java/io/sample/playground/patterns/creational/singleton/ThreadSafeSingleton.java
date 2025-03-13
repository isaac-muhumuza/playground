package io.sample.playground.patterns.creational.singleton;

/**
 * ThreadSafeSingleton - threadsafe singleton pattern, the static factory method returns the instance of the singleton class
 */
public class ThreadSafeSingleton {

    private ThreadSafeSingleton() {

    }
    private static class ThreadSafeSingletonHolder {
        private static final ThreadSafeSingleton INSTANCE = new ThreadSafeSingleton();
    }

    public static ThreadSafeSingleton getInstance() {
        return ThreadSafeSingletonHolder.INSTANCE;
    }
}
