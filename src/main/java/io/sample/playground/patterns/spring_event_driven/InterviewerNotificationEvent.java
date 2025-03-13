package io.sample.playground.patterns.spring_event_driven;

import org.springframework.context.ApplicationEvent;

public class InterviewerNotificationEvent extends ApplicationEvent {

    public InterviewerNotificationEvent(Interviewee source) {
        super(source);
    }
}
