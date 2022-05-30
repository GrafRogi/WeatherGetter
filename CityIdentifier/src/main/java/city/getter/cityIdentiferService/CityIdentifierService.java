package city.getter.cityIdentiferService;

import org.example.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CityIdentifierService {

    @Value("${APIkey}")
    private String APIkey;

    public ResponseEntity<String> send(MessageDTO messageDTO) {
        return new RestTemplate().exchange(createUri(messageDTO), HttpMethod.POST, new HttpEntity<String>());//Вот тут сам исправляй, не знаю что какая там должна быть Entity
        // ещё нужно отпарсить то что ты получил и получить город, обновить messageDTO  отправить его на Sender
    }

    private String createUri(MessageDTO messageDTO) {
        return String.format("http://htmlweb.ru/geo/api.php?html&telcod=%s&api_key=%s", messageDTO.getPhoneNumber(),
                APIkey);
    }
}
