package io.sample.playground.mq;

import io.sample.playground.dto.MQMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static io.sample.playground.config.RabbitMQConfig.EXCHANGE_NAME;
import static io.sample.playground.config.RabbitMQConfig.QUEUE_NAME;

@Component
public class DemoQueue {

    private final AmqpTemplate amqpTemplate;

    public DemoQueue(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void publish(@Payload MQMessage mqMessage) {
        amqpTemplate.convertAndSend(EXCHANGE_NAME, QUEUE_NAME, mqMessage);
    }
}
