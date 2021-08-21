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
public class PersonalInformations {
    private String studentName;
    @Id
    private String studentId;
    private String className;
    private String personalEmail;
    private String phone;
    private String birthday;
    private String schoolMail;
    private String medicalId;
    private String medicalIdEnd;
    private String personalImage;
    @OneToMany(mappedBy = "personalInformation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Schedules> schedulesSet;
    @OneToMany(mappedBy = "personalInformation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Friends> friendsSet;
    @OneToMany(mappedBy = "personalInformation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Tests> testsSet;
    @OneToMany(mappedBy = "personalInformation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<StudyResults> studyResultsSet;
    @OneToOne(mappedBy = "personalInformation", fetch = FetchType.LAZY)
    @Transient
    private Morals moral;
    @OneToOne(fetch = FetchType.LAZY)
    private Users user;

}