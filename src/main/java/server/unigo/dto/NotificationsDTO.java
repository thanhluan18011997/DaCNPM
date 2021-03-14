package server.unigo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class NotificationsDTO {
    @JsonProperty("title")
    String title;
    @JsonProperty("content")
    String content;
}
