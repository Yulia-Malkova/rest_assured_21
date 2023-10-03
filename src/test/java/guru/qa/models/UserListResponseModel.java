package guru.qa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserListResponseModel {
    int page;
    @JsonProperty("per_page")
    int perPage;
    @JsonProperty("total_pages")
    int totalPages;
    int total;
    List<DataModel> data;
    SupportModel support;
}
