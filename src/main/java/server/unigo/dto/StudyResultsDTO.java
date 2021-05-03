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
    @JsonProperty("BT")
    String BT;
    @JsonProperty("BV")
    String BV;
    @JsonProperty("CC")
    String CC;
    @JsonProperty("CK")
    String CK;
    @JsonProperty("DA")
    String DA;
    @JsonProperty("GK")
    String GK;
    @JsonProperty("LT")
    String LT;
    @JsonProperty("TH")
    String TH;
    @JsonProperty("T10")
    String T10;
    @JsonProperty("T4")
    String T4;
    @JsonProperty("as_text")
    String asText;


}
