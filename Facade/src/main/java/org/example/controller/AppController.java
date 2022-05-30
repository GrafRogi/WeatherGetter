package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.example.rabbit.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class AppController {

    @Autowired
    RabbitSender sender;

    @GetMapping("/send/{phoneNumber}")
    public void getPhoneNumber(@PathVariable String phoneNumber){
        System.out.println("Controller invoke sender.send() with "+phoneNumber);
        sender.sendToCityIdentifier(phoneNumber);
        log.info("Controller invoke sender.send() with "+phoneNumber);
    }
}




