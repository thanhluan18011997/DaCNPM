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
public class PersonalInformations  {
    String studentName;
    @Id
    String studentId;
    String className;
    String personalEmail;
    String phone;
    String birthday;
    String schoolMail;
    String medicalId;
    String medicalIdEnd;
    String personalImage;
    @OneToMany(mappedBy = "personalInformation",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    Set<Schedules> schedulesSet;
    @OneToMany(mappedBy = "personalInformation",cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    Set<Tests> testsSet;
    @OneToMany(mappedBy = "personalInformation",cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    Set<StudyResults> studyResultsSet;
    @OneToOne(mappedBy = "personalInformation",fetch = FetchType.LAZY )
    Morals moral;

}