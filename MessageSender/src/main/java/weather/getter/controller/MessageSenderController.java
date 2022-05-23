package weather.getter.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import weather.getter.model.MessageRequest;
import weather.getter.service.MessageSenderService;

@RestController
public class MessageSenderController {

  private final String login;

  private final String password;

  private final MessageSenderService service;

  public MessageSenderController(@Value("${login}") String login, @Value("${password}") String password,
      MessageSenderService service) {
    this.login = login;
    this.password = password;
    this.service = service;
  }

  @PostMapping(value = "/message", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> sendMessageController(@RequestBody MessageRequest request) {
    return new RestTemplate().exchange(service.createUri(request), HttpMethod.GET,
        new HttpEntity<String>(service.createHeaders(login, password)),
        String.class);
  }
}
