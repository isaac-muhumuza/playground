package io.sample.playground.patterns.spring_event_driven;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SubmittedApplicationListener implements ApplicationListener<InterviewerNotificationEvent> {
    @Override
    public void onApplicationEvent(InterviewerNotificationEvent event) {
        Interviewee interviewee = (Interviewee) event.getSource();
        log.info("Interview date set for {} on {}", interviewee.getNameOfApplicant(), interviewee.getInterviewDate());
    }
}
