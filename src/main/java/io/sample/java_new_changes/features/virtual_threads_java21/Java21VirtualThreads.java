package io.sample.java_new_changes.features.virtual_threads_java21;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Introduction to the new virtual thread impl in java21
 * Introduced to address limitations with traditional OS threads
 * Use of OS threads meant that there was a limit to number of threads and once this was reached it would lead to app crash or resource exhaustion
 * Another limitation was it was difficult to scale applications with this limitation.
 * Virtual threads are lightweight and can be created in large numbers
 * - Requires java21 pom change
 */
@Slf4j
public class Java21VirtualThreads {

    public static void OSBasedThreadImpl(int threadCount) {
        List<Thread> workerThreads = new ArrayList<>();

        IntStream.range(0, threadCount).forEach(i -> {
            Thread thread = new Thread(() -> new ThreadTask().handleRequest());
            thread.setName("Thread-" + i);
            thread.start();
            log.info("Thread {} started", thread.getName());
            workerThreads.add(thread);
        });

        workerThreads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                log.error("Thread {} interrupted", thread.getName(), e);
                Thread.currentThread().interrupt();
            }
        });

    }

    public static void newVirtualThreadImpl(int threadCount) {
        List<Thread> workerThreads = new ArrayList<>();

        IntStream.range(0, threadCount).forEach(i -> {
            // using virtual threads
            /*Thread thread = Thread.ofVirtual().unstarted(() -> new ThreadTask().handleRequest());
            thread.setName("Thread-" + i);
            thread.start();
            log.info("Thread {} started", thread.getName());
            workerThreads.add(thread);*/
        });

        workerThreads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                log.error("Thread {} interrupted", thread.getName(), e);
                Thread.currentThread().interrupt();
            }
        });
        log.info(" All {} threads have been completed", workerThreads.size());
    }

    public static void main(String[] args) {

        //classical way of handling threads should run out of memory when using 5000 threads
        //OSBasedThreadImpl(5000);

        //new virtual thread impl should be able to handle any number of threads
        newVirtualThreadImpl(10000);
    }
}
