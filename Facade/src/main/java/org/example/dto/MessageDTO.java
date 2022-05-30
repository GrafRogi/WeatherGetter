package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Artyom Kulagin
 */
@Data
@RequiredArgsConstructor
public class MessageDTO {
    private final String phoneNumber;
    private String city;
    private String message;
}
