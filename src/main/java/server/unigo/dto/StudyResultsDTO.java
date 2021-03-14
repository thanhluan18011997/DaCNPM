package server.unigo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudyResultsDTO {
    @JsonProperty("index")
    Long index_;
    String semester;
    @JsonProperty("course_code")
    String courseCode;
    @JsonProperty("course_name")
    String courseName;
    Double credit;
    @JsonProperty("point_formular")
    String pointFormula;
    Double BT;
    Double BV;
    Double CC;
    Double CK;
    Double DA;
    Double GK;
    Double LT;
    Double TH;
    Double T10;
    Double T4;
    @JsonProperty("as_text")
    String asText;


}
