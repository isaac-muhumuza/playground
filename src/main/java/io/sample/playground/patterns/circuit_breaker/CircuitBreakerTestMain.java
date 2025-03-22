package io.sample.playground.patterns.circuit_breaker;

import lombok.extern.slf4j.Slf4j;

/**
 * Simple code implementation of a circuit Breaker pattern,
 * It helps ensure fault tolerance and resilience in microservices and distributed systems,
 * by preventing a system from repeatedly trying to execute an operation that is likely to fail,
 * therefore allowing it to recover from faults and prevent cascading failures.
 **/

@Slf4j
public class CircuitBreakerTestMain {

    public static void main(String[] args) {

        var serverStartTimeInMills = System.nanoTime();

        var delayedService = new RemoteService(serverStartTimeInMills, 5000*1000*1000L);
        var delayedCircuitBreaker = new CircuitBreaker(delayedService, 2);

        var quickService = new RemoteService();
        var quickCircuitBreaker = new CircuitBreaker(quickService, 2);

        var monitoringService = new MonitoringService(delayedCircuitBreaker, quickCircuitBreaker);

        log.info("Starting monitoring service:");

        monitoringService.serviceResponse(quickCircuitBreaker);
        monitoringService.serviceResponse(quickCircuitBreaker);
        monitoringService.serviceResponse(quickCircuitBreaker);

        log.info("State of quick circuit breaker: {}", quickCircuitBreaker.getCircuitBreakerState());

        // receive two unresponsive responses to trigger threshold and circuit breaker
        monitoringService.serviceResponse(delayedCircuitBreaker);
        monitoringService.serviceResponse(delayedCircuitBreaker);

        log.info("State of delayed circuit breaker: {}", delayedCircuitBreaker.getCircuitBreakerState());

        // unresponsive responses are dropped
        monitoringService.serviceResponse(delayedCircuitBreaker);
        monitoringService.serviceResponse(delayedCircuitBreaker);


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.error("Error: {}", e.getMessage());
            Thread.currentThread().interrupt();
        }


        monitoringService.serviceResponse(delayedCircuitBreaker);
        log.info("State of delayed circuit breaker: {}", delayedCircuitBreaker.getCircuitBreakerState());

        monitoringService.serviceResponse(delayedCircuitBreaker);
        log.info("State of delayed circuit breaker: {}", delayedCircuitBreaker.getCircuitBreakerState());

    }
}
