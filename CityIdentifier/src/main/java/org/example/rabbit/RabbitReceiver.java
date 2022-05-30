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
    ObjectMapper objectMapper;

    @RabbitListener(containerFactory = "myRabbitListenerContainerFactory",queues = "cityIdentifierInQueue")
    public void receive(Message message) {
        MessageDTO messageDTO = convertToDTO(message);
        System.out.println("Message " + messageDTO + " was received");
        log.info("Message " + messageDTO + " was received");
        //Вот тут надо вызвать метод бизнес-логики
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
