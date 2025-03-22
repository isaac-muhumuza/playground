package io.sample.playground.patterns.circuit_breaker;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class RemoteService {

    private long serverStartTimeInMills;
    private long serverUnresponsiveTimeInMillis;
    private int delayedResponseCount = 0;

    public RemoteService() {
        this.serverStartTimeInMills = System.nanoTime();
        this.serverUnresponsiveTimeInMillis = 0L;
    }

    public RemoteService(long serverStartTimeInMills, long serverUnresponsiveTimeInMillis) {
        this.serverStartTimeInMills = serverStartTimeInMills;
        this.serverUnresponsiveTimeInMillis = serverUnresponsiveTimeInMillis;
    }

    public boolean isServerResponsive() {
        log.info("isServerResponsive: {}", System.nanoTime() - serverStartTimeInMills >= serverUnresponsiveTimeInMillis);
        return System.nanoTime() - serverStartTimeInMills >= serverUnresponsiveTimeInMillis;
    }
}
