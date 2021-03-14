package server.unigo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MoralsDTO {
    String semester;
    @JsonProperty("registered_credit")
    Double registeredCredit;
    @JsonProperty("relearn_credit")
    Double relearnCredit;
    @JsonProperty("avg_b4")
    Double avgB4;
    @JsonProperty("avg_scholar")
    Double avgScholar;
    @JsonProperty("avg_b10")
    Double avgB10;
    @JsonProperty("study_classify")
    String studyClassify ;
    @JsonProperty("moral_points")
    Double moralPoints;
    @JsonProperty("warnings")
    String warnings;
    @JsonProperty("saved_credits")
    Double savedCredits;
    @JsonProperty("avg_saved_credit_b4")
    Double avgSavedCreditB4;
    @JsonProperty("avg_moral")
    Double avgMoral;

}
