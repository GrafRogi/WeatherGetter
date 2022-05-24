package weather.getter.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class PhoneController {

    private final String phoneNumber;

    private final CityService cityService;

    public PhoneController(String phoneNumber, CityService cityService) {
        this.phoneNumber = phoneNumber;
        this.cityService = cityService;
    }


    @GetMapping("/phonenumber{phoneNumber}")
   void getPhoneNumber(@PathVariable String phoneNumber) {
        }
}




