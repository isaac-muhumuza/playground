package io.sample.playground.rest;

import io.sample.playground.config.ServiceModeCondition;
import io.sample.playground.dto.MQMessage;
import io.sample.playground.service.DemoService;
import io.sample.playground.patterns.spring_event_driven.Interviewee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Conditional(ServiceModeCondition.class)
@Slf4j
@RestController
@RequestMapping("/playground")
public class DemoController {

    private DemoService service;

    public DemoController(DemoService service) {
        this.service = service;
    }

    @PostMapping(value = "/send-message", consumes = "application/json")
    public void sendMessage(@RequestBody MQMessage mqMessage) {
        log.info("Received request to send: {}", mqMessage);
        service.sendMessage(mqMessage);
    }

    @GetMapping(value = "/object/{responseObjectId}", produces = "application/json")
    public String getObjectId(@PathVariable("responseObjectId") String responseObjectId) throws IOException {
        log.info("Received object request for: {}", responseObjectId);
        return service.getResponseObject(responseObjectId);
    }

    // Add a new endpoint for interviewee
    @PostMapping(value = "/apply-for-job", consumes = "application/json")
    public void applyForJob(@RequestBody Interviewee interviewee) {
        log.info("Received external application for {}" + interviewee.getNameOfApplicant());
        interviewee.setInterviewDate(Date.from(new Date().toInstant().plus(12, ChronoUnit.DAYS)));
        service.submitApplication(interviewee);
    }
}
