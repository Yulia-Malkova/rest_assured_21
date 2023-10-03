package guru.qa.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
public class UserCreationResponseModel {
    private String name, job;
    private int id;
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Date createdAt;
}
