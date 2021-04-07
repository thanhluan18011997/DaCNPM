package server.unigo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Morals extends BaseEntity {
    private String semester;
    private Double registeredCredit;
    private Double relearnCredit;
    private Double avgB4;
    private Double avgScholar;
    private Double avgB10;
    private String studyClassify ;
    private Double moralPoints;
    private String warnings;
    private Double savedCredits;
    private Double avgSavedCreditB4;
    private Double avgMoral;
    @OneToOne(fetch = FetchType.LAZY)
    private PersonalInformations personalInformation;
}
