package org.example.rabbit;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.cityIdentifierService.Service;
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
@Slf4j
@EnableRabbit
public class RabbitReceiver {

    @Autowired
    Service service;
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(containerFactory = "myRabbitListenerContainerFactory",queues = "cityIdentifierInQueue")
    public void receive(Message message) {
        MessageDTO messageDTO = convertToDTO(message);
        System.out.println("Message " + messageDTO + " was received");
        log.info("Message " + messageDTO + " was received");
        service.send(messageDTO);
    }

    private MessageDTO convertToDTO(Message message) {
        try {
            byte[] bytes = message.getBody();
            String json = new String(bytes);
            return objectMapper.readValue(json, new TypeReference<MessageDTO>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
