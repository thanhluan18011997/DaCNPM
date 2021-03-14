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
    Long index_;
    String semester;
    String courseCode;
    String courseName;
    Double credit;
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
    String asText;
    @ManyToOne()
    PersonalInformations personalInformation;


}
