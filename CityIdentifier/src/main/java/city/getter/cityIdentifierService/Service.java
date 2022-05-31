package city.getter.cityIdentifierService;

import org.json.JSONObject;
import org.example.dto.MessageDTO;
import org.example.rabbit.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@org.springframework.stereotype.Service
public class Service {

    //    @Value("${APIkey}")
    private String APIkey = "9444784564e3fd023a67421297ccec56";

    @Autowired
    RabbitSender sender;

    public String send(MessageDTO messageDTO) {
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(createUri(messageDTO));
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
        String city = obj.getJSONObject("0").getString("name");
        messageDTO.setCity(city);
        sender.send(messageDTO);
        return city;
    }

    private String createUri(MessageDTO messageDTO) {
        return String.format("http://htmlweb.ru/geo/api.php?json&telcod=%s&api_key=%s", messageDTO.getPhoneNumber(),
                APIkey);
    }
}
