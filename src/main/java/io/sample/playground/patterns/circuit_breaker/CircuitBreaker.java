package io.sample.playground.patterns.circuit_breaker;

import lombok.Data;

@Data
public class CircuitBreaker {

    private RemoteService remoteService;
    private int retryTimeoutInMillis = 0;
    private CircuitBreakerState circuitBreakerState = CircuitBreakerState.CLOSED;

    public CircuitBreaker(RemoteService remoteService, int retryTimeoutInMillis) {
        this.remoteService = remoteService;
        this.retryTimeoutInMillis = retryTimeoutInMillis;
    }


}
