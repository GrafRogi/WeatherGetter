package city.getter.cityNumberIdentifierController;

import city.getter.cityIdentiferModel.CityIdentifierRequest;
import city.getter.cityIdentiferService.CityIdentiferService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

public class CityIdentiferController {

    private final CityIdentiferService service;

    public CityIdentiferController(CityIdentiferService service) {
        this.service = service;
    }

    @PostMapping(value = "/city", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> identifyCityController(@RequestBody CityIdentifierRequest request) {
        return new RestTemplate().exchange(service.createUri(request), HttpMethod.POST, new HttpEntity<String>() );
//        return new RestTemplate().exchange(service.createUri(request), HttpMethod.POST,
//                new HttpEntity<String>(),
//                String.class);
    }






}
