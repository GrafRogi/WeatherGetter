package org.example.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Artyom Kulagin
 */
@Component
@Slf4j
public class RabbitSender {

    @Autowired
    private RabbitTemplate template;

    public void send(String message) {
        template.convertAndSend(message);
        log.info("Message " + message + " send");
    }
}
