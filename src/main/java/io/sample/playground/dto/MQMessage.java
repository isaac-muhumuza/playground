package io.sample.playground.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class MQMessage implements Serializable {

    private String name;

    private String details;
}
