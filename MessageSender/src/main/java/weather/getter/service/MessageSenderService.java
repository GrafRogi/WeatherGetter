package weather.getter.service;

import java.nio.charset.Charset;
import java.util.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import weather.getter.model.MessageRequest;

@Service
public class MessageSenderService {

  public String createUri(MessageRequest request) {
    return String.format("https://api.iqsms.ru/messages/v2/send/?phone=%s&text=%s", request.getPhoneNumber(),
        request.getMessage());
  }

  public HttpHeaders createHeaders(String username, String password) {
    return new HttpHeaders() {{
      String auth = username + ":" + password;
      byte[] encodedAuth = Base64.getEncoder().encode(
          auth.getBytes(Charset.forName("US-ASCII")));
      String authHeader = "Basic " + new String(encodedAuth);
      set("Authorization", authHeader);
    }};
  }
}
