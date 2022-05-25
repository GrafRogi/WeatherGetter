package city.getter.cityIdentiferService;

import city.getter.cityIdentiferModel.CityIdentifierRequest;

public class CityIdentiferService {

    public String createUri(CityIdentifierRequest request) {

        return String.format("http://htmlweb.ru/geo/api.php?%s&telcod=%s&api_key=%s", request.getRequestFormat() , request.getPhoneNumber(),
                request.getAPIkey());

    }

}
