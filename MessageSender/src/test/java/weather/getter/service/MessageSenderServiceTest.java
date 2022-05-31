package weather.getter.service;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Artyom Kulagin
 */

public class MessageSenderServiceTest {

    @Mock
    RestTemplate restTemplate;

    @Test
    public void shouldReturnCurrentCode() {

    }


}
