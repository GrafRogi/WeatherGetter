package org.example;

import org.example.dto.MessageDTO;
import org.example.rabbit.RabbitSender;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class WeatherReporter {

    @Autowired
    RabbitSender sender;

    static String key = "2d994807cab84e7a791bddf1f7c7fcc0";

    public void getContent(MessageDTO messageDTO) {
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + messageDTO.getCity() + "&appid=" + key);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Такого города нет");
        }
        JSONObject obj = new JSONObject(content.toString());
        double temp = Math.floor(obj.getJSONObject("main").getDouble("temp") - 273);
        if (temp < 15) {
            messageDTO.setMessage("Cold!");
        } else {
            messageDTO.setMessage("Hot!");
        }
        sender.send(messageDTO);
    }

    //Вот это надо будет снести перед демонстрацией
    public static void main(String[] args) {
        String yourCity = "Moscow";

        MessageDTO messageDTO = new MessageDTO("");
        messageDTO.setCity(yourCity);
        WeatherReporter reporter = new WeatherReporter();
        reporter.getContent(messageDTO);
    }
}