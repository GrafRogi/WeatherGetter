package weather.getter.dto;

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
