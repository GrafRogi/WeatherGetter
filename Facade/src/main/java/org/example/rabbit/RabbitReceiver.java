package org.example.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.MessageDTO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Artyom Kulagin
 */
@Component
@EnableRabbit
@Slf4j
public class RabbitReceiver {

    @Autowired
    private RabbitSender sender;
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(containerFactory = "myRabbitListenerContainerFactory", queues = "cityIdentifierOutQueue")
    public void receiveFromCityIdentifier(Message message) {
        System.out.println("Message " + message + " was received");
        log.debug("Message " + message + " was received");
        sender.sendToWeatherReporter(message);
    }

    @RabbitListener(containerFactory = "myRabbitListenerContainerFactory", queues = "weatherReporterOutQueue")
    public void receiveFromWeatherReporter(Message message) {
        System.out.println("Message " + message + " was received");
        log.info("Message " + message + " was received");
        sender.sendToMessageSender(message);
    }

    private MessageDTO convertToDTO(Message message) {
        try {
            return objectMapper.readValue(message.getBody(), MessageDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
