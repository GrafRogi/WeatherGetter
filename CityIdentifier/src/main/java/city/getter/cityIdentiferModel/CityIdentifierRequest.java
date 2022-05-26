package city.getter.cityIdentiferModel;

public class CityIdentifierRequest {

    private String requestFormat;
    private String phoneNumber;
    private String APIkey = "9444784564e3fd023a67421297ccec56";

    public CityIdentifierRequest() {
    }

    public String getRequestFormat() {
        return requestFormat;
    }

    public void setRequestFormat(String requestFormat) {
        this.requestFormat = requestFormat;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAPIkey() {
        return APIkey;
    }



    @Override
    public String toString() {
        return "CityIdentifierRequest{" +
                "phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

