package org.example.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Artyom Kulagin
 */
@Component
@EnableRabbit
@Slf4j
public class RabbitReceiver {

    @RabbitListener(queues = "one")
    public void receive(String message) {
        log.info("Message " + message + " was receive");
    }
}
