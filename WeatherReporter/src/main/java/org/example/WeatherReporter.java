package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class WeatherReporter {

    static String key = "2d994807cab84e7a791bddf1f7c7fcc0";

    static String getContent(String city) {
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + key);
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
        return content.toString();
    }

    public static void main(String[] args) {
        String yourCity = "Moscow";

        String jsonContent = getContent(yourCity);
        System.out.println(jsonContent);

        if (!jsonContent.isEmpty()) {
            JSONObject obj = new JSONObject(jsonContent);
            String s = String.format("Temp in %s = %s", yourCity, Math.floor(obj.getJSONObject("main").getDouble("temp") - 273));
            System.out.println(s);
        }
    }
}