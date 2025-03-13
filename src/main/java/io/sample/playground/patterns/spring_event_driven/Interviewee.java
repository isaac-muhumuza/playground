package io.sample.playground.patterns.spring_event_driven;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Interviewee {
    private String nameOfApplicant;
    private int phoneNumber;
    @Email
    private String email;
    private Date interviewDate;
}
