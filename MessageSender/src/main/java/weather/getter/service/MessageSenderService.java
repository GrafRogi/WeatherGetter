package weather.getter.service;

import java.nio.charset.Charset;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import weather.getter.dto.MessageDTO;

@Service
@PropertySource("classpath:application.properties")
public class MessageSenderService {

//    @Value("${login}")
    private final String login="z1653314168211";
//    @Value("${password}")
    private final String password="433186";

    public void send(MessageDTO messageDTO) {
        new RestTemplate().exchange(createUri(messageDTO), HttpMethod.GET,
                new HttpEntity<String>(createHeaders()),
                String.class);
    }

    public String createUri(MessageDTO request) {
        return String.format("https://api.iqsms.ru/messages/v2/send/?phone=%s&text=%s", request.getPhoneNumber(),
                request.getMessage());
    }

    public HttpHeaders createHeaders() {
        return new HttpHeaders() {{
            String auth = login + ":" + password;
            byte[] encodedAuth = Base64.getEncoder().encode(
                    auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};
    }
}
