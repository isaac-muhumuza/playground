package io.sample.playground.subscriber;

import io.sample.playground.config.ProcessorModeCondition;
import io.sample.playground.dto.MQMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import static io.sample.playground.config.RabbitMQConfig.EXCHANGE_NAME;
import static io.sample.playground.config.RabbitMQConfig.QUEUE_NAME;

@Conditional(ProcessorModeCondition.class)
@Slf4j
@Component
public class DemoSubscriber {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(QUEUE_NAME),
                    exchange = @Exchange(EXCHANGE_NAME)
            )
    })
    public void handleDelivery(MQMessage mqMessage) {
        log.info("Handling message: {}", mqMessage);
    }
}
