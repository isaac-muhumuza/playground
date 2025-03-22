package io.sample.playground.patterns.circuit_breaker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class MonitoringServiceTest {

    private CircuitBreaker delayedCircuitBreaker;
    private CircuitBreaker quickCircuitBreaker;
    @InjectMocks
    private MonitoringService monitoringService;


    @BeforeEach
    void setUp() {
        RemoteService delayedRemoteService = new RemoteService(System.nanoTime(), 5000 * 1000 * 1000L);
        RemoteService quickRemoteService = new RemoteService();

        delayedCircuitBreaker = new CircuitBreaker(delayedRemoteService, 2);
        quickCircuitBreaker = new CircuitBreaker(quickRemoteService, 2);

        monitoringService = new MonitoringService(delayedCircuitBreaker, quickCircuitBreaker);

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void when_a_delayed_remote_service_hits_the_delayed_responses_threshod_then_open_the_circuit_breaker() {
        assertThat(delayedCircuitBreaker.getRemoteService().getDelayedResponseCount(), equalTo(0));
        assertThat(delayedCircuitBreaker.getCircuitBreakerState(), equalTo(CircuitBreakerState.CLOSED));

        // receive two unresponsive responses to trigger threshold and circuit breaker
        monitoringService.serviceResponse(delayedCircuitBreaker);
        monitoringService.serviceResponse(delayedCircuitBreaker);

        assertThat(delayedCircuitBreaker.getRemoteService().getDelayedResponseCount(), equalTo(2));
        assertThat(delayedCircuitBreaker.getCircuitBreakerState(), equalTo(CircuitBreakerState.OPEN));

    }

    @Test
    void when_a_delayed_remote_service_is_now_resposive_then_the_circuit_breaker_should_be_half_open_and_open() {
        assertThat(delayedCircuitBreaker.getRemoteService().getDelayedResponseCount(), equalTo(0));
        assertThat(delayedCircuitBreaker.getCircuitBreakerState(), equalTo(CircuitBreakerState.CLOSED));

        // receive two unresponsive responses to trigger threshold and circuit breaker
        monitoringService.serviceResponse(delayedCircuitBreaker);
        monitoringService.serviceResponse(delayedCircuitBreaker);

        assertThat(delayedCircuitBreaker.getRemoteService().getDelayedResponseCount(), equalTo(2));
        assertThat(delayedCircuitBreaker.getCircuitBreakerState(), equalTo(CircuitBreakerState.OPEN));

        try {
            // allow a delay for the server to be responsive
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        assertThat(delayedCircuitBreaker.getCircuitBreakerState(), equalTo(CircuitBreakerState.OPEN));

        // a responsive response should change the circuit breaker state to half open
        monitoringService.serviceResponse(delayedCircuitBreaker);

        assertThat(delayedCircuitBreaker.getCircuitBreakerState(), equalTo(CircuitBreakerState.HALF_OPEN));

        // a further responsive response should change the circuit breaker state to closed
        monitoringService.serviceResponse(delayedCircuitBreaker);

        assertThat(delayedCircuitBreaker.getCircuitBreakerState(), equalTo(CircuitBreakerState.CLOSED));
    }

    @Test
    void when_receiving_resposnses_from_quick_remote_service_without_delayes_then_the_circuti_breaker_is_not_triggered() {
        assertThat(quickCircuitBreaker.getRemoteService().getDelayedResponseCount(), equalTo(0));
        assertThat(quickCircuitBreaker.getCircuitBreakerState(), equalTo(CircuitBreakerState.CLOSED));

        // receive two or more responsive responses do not trigger threshold and circuit breaker
        monitoringService.serviceResponse(quickCircuitBreaker);
        monitoringService.serviceResponse(quickCircuitBreaker);
        monitoringService.serviceResponse(quickCircuitBreaker);

        assertThat(quickCircuitBreaker.getRemoteService().getDelayedResponseCount(), equalTo(0));
        assertThat(quickCircuitBreaker.getCircuitBreakerState(), equalTo(CircuitBreakerState.CLOSED));
    }
}
