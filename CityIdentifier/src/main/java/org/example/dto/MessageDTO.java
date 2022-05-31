package org.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
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
