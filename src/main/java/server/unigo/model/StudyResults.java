package server.unigo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class StudyResults extends BaseEntity{
    private Long index_;
    private String semester;
    private String courseCode;
    private String courseName;
    private Double credit;
    private String pointFormula;
    private Double BT;
    private Double BV;
    private Double CC;
    private Double CK;
    private Double DA;
    private Double GK;
    private Double LT;
    private Double TH;
    private Double T10;
    private Double T4;
    private String asText;
    @ManyToOne()
    private PersonalInformations personalInformation;


}
