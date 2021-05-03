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
    private String BT;
    private String BV;
    private String CC;
    private String CK;
    private String DA;
    private String GK;
    private String LT;
    private String TH;
    private String T10;
    private String T4;
    private String asText;
    @ManyToOne()
    private PersonalInformations personalInformation;


}
