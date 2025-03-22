package io.sample.playground.patterns.circuit_breaker;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class MonitoringService {

    private CircuitBreaker delayedCircuitBreaker;
    private CircuitBreaker quickCircuitBreaker;
    private static final int FAILURE_RESPONSE_THRESHOLD = 2;
    private static final int SERVER_RETRY_TIMEOUT = 3000;

    public MonitoringService(CircuitBreaker delayedCircuitBreaker, CircuitBreaker quickCircuitBreaker) {
        this.delayedCircuitBreaker = delayedCircuitBreaker;
        this.quickCircuitBreaker = quickCircuitBreaker;
    }

    public void serviceResponse(CircuitBreaker circuitBreaker) {
        if (!circuitBreaker.getRemoteService().isServerResponsive()) {
            log.info("Server is unresponsive");
            if (circuitBreaker.getCircuitBreakerState() == CircuitBreakerState.CLOSED) {
                log.info("Circuit breaker is closed, incrementing delayed response count");
                circuitBreaker.getRemoteService().setDelayedResponseCount(circuitBreaker.getRemoteService().getDelayedResponseCount() + 1);

                // check if it exceeds threshold and change state to open
                if (circuitBreaker.getRemoteService().getDelayedResponseCount() >= FAILURE_RESPONSE_THRESHOLD) {
                    circuitBreaker.setCircuitBreakerState(CircuitBreakerState.OPEN);
                    log.info("Circuit breaker exceeded response threshold at: {} and now is: {}",
                            circuitBreaker.getRemoteService().getDelayedResponseCount(), circuitBreaker.getCircuitBreakerState());
                }
            }
            // if no longer un-responisive but state is open, change to half open
        } else if (circuitBreaker.getCircuitBreakerState() == CircuitBreakerState.OPEN) {
            circuitBreaker.setCircuitBreakerState(CircuitBreakerState.HALF_OPEN);
            circuitBreaker.getRemoteService().setDelayedResponseCount(circuitBreaker.getRemoteService().getDelayedResponseCount() -1);
            log.info("Server was unresponsive, changing circuit breaker state to {}}", circuitBreaker.getCircuitBreakerState());

        } else {
            circuitBreaker.setCircuitBreakerState(CircuitBreakerState.CLOSED);
            circuitBreaker.getRemoteService().setDelayedResponseCount(0);
            log.info("Server is responsive with state {}", circuitBreaker.getCircuitBreakerState());
        }

    }
}

