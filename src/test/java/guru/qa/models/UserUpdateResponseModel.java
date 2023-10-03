package guru.qa.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
public class UserUpdateResponseModel {
    private String name, job;
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Date updatedAt;
}
