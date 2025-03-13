package io.sample.playground.patterns.spring_event_driven;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobApplication implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;


    public void applyForJob(Interviewee interviewee) {
        log.info("Application published for " + interviewee.getNameOfApplicant() + " with email " + interviewee.getEmail() + " and phone number " + interviewee.getPhoneNumber());
        applicationEventPublisher.publishEvent(new InterviewerNotificationEvent(interviewee));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
