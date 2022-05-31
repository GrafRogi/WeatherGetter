package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * @author Artyom Kulagin
 */
@Data
public class MessageDTO implements Serializable {
    private String phoneNumber;
    private String city;
    private String message;
}
