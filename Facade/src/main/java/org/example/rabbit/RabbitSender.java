package org.example.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.MessageDTO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
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
    @Autowired
    private ObjectMapper objectMapper;

    public void sendToCityIdentifier(String message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setPhoneNumber(message);
        template.convertAndSend("cityIdentifierInQueue",mapToRabbitMessage(messageDTO));
        System.out.println("In sendToCityIdentifier() message " + messageDTO + " send");
        log.info("In sendToCityIdentifier() message " + messageDTO + " send");
    }

    public void sendToWeatherReporter(Message message) {
        template.send("weatherReporterInQueue", message);
        System.out.println("Message " + message + " send");
        log.info("In sendToWeatherReporter() message " + message + " send");
    }

    public void sendToMessageSender(Message message)  {
        template.send("messageSenderInQueue",message);
        System.out.println("Message " + message + " send");
        log.info("In sendToMessageSender() message " + message + " send");
    }

    private Message mapToRabbitMessage(MessageDTO messageDTO) {
        String stringMessage = null;
        try {
            stringMessage = objectMapper.writeValueAsString(messageDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return MessageBuilder
                .withBody(stringMessage.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();
    }
}
