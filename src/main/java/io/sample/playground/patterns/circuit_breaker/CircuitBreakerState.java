package io.sample.playground.patterns.circuit_breaker;

public enum CircuitBreakerState {
    OPEN,
    HALF_OPEN,
    CLOSED
}
