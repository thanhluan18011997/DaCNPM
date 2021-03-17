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
    String semester;
    Double registeredCredit;
    Double relearnCredit;
    Double avgB4;
    Double avgScholar;
    Double avgB10;
    String studyClassify ;
    Double moralPoints;
    String warnings;
    Double savedCredits;
    Double avgSavedCreditB4;
    Double avgMoral;
    @OneToOne(fetch = FetchType.LAZY)
    PersonalInformations personalInformation;
}
