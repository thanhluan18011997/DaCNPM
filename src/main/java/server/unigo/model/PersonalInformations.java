package server.unigo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PersonalInformations extends BaseEntity {
    String studentName;
    String studentId;
    String className;
    String personalEmail;
    String phone;
    String birthday;
    String schoolMail;
    String medicalId;
    String medicalIdEnd;
    String personalImage;
    @OneToMany(mappedBy = "personalInformation")
    Set<Schedules> schedulesSet;
    @OneToMany(mappedBy = "personalInformation")
    Set<Tests> testsSet;
    @OneToMany(mappedBy = "personalInformation")
    Set<StudyResults> studyResultsSet;
    @OneToOne(mappedBy = "personalInformation")
    Morals moral;

}