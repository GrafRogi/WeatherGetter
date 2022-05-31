package org.example.Servise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.MessageDTO;
import org.example.rabbit.RabbitSender;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Service
public class WeatherReporter {

    @Autowired
    RabbitSender sender;

    static String key = "2d994807cab84e7a791bddf1f7c7fcc0";

    public void getContent(MessageDTO messageDTO) {
        RestTemplate restTemplate = new RestTemplate();
        String url = createUri(messageDTO);
        ResponseEntity<String> response
                = restTemplate.getForEntity(url, String.class);
        try {
            JsonNode object = new ObjectMapper().readTree(response.getBody());
            JsonNode test = object.path("main").path("temp");
            System.out.println(test);
            double temp = test.asInt() - 273;
            if (temp < 15) {
                messageDTO.setMessage("Cold!");
            } else {
                messageDTO.setMessage("Hot!");
            }
        } catch (
                JsonProcessingException e) {
            e.printStackTrace();
        }
            sender.send(messageDTO);
    }

    private String createUri(MessageDTO messageDTO) {
        return String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", messageDTO.getCity(),
                key);
    }

    //Вот это надо будет снести перед демонстрацией
    public static void main(String[] args) {
        String yourCity = "Санкт-Петербург";

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setCity(yourCity);
        WeatherReporter reporter = new WeatherReporter();
        reporter.getContent(messageDTO);
    }
}