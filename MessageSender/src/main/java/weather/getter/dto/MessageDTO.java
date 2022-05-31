package weather.getter.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Artyom Kulagin
 */
@Data
public class MessageDTO {
    private String phoneNumber;
    private String city;
    private String message;
}
