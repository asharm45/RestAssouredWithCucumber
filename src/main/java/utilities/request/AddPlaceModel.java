package utilities.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AddPlaceModel {
    private Location location;
    private Integer accuracy;
    private String name;
    private String phone_number;
    private String address;
    private List<String> types = null;
    private String website;
    private String language;
}
