package server.unigo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class TestsDTO {
    @JsonProperty("index")
    Long index_;
    @JsonProperty("course_code")
    String courseCode;
    @JsonProperty("course_name")
    String courseName;
    @JsonProperty("test_group")
    String testGroup;
    @JsonProperty("test_grouping")
    String testGrouping;
    @NotBlank
    @JsonProperty("test_schedule")
    String testSchedule;
}
