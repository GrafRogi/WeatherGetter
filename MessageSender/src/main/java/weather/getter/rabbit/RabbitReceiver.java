package weather.getter.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import weather.getter.dto.MessageDTO;
import weather.getter.service.MessageSenderService;

import java.io.IOException;

/**
 * @author Artyom Kulagin
 */
@Component
@EnableRabbit
@Slf4j
public class RabbitReceiver {

    @Autowired
    private MessageSenderService service;
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(containerFactory = "myRabbitListenerContainerFactory",queues = "messageSenderInQueue")
    public void receive(Message message) {
        service.send(convertToDTO(message));
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
