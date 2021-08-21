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
public class Schedules extends BaseEntity {
    private Long index_;
    private String courseCode;
    private String courseName;
    private Double credit;
    private String teacher;
    private String studyWeeks;
    @ManyToOne()
    private PersonalInformations personalInformation;
    @ManyToOne()
    private Semesters semester;
    @OneToOne(mappedBy = "schedules",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private WeeklySchedules weeklySchedules;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "schedule_collaborator",joinColumns = @JoinColumn(name="id_schedule"),inverseJoinColumns = @JoinColumn(name="id_collaborator"))
    private Set<Collaborators>collaborators;
    public Schedules(Long index_, String courseCode, String courseName, Double credit, String teacher, String studyWeeks){
        this.index_=index_;
        this.courseCode=courseCode;
        this.courseName=courseName;
        this.credit=credit;
        this.teacher=teacher;
        this.studyWeeks=studyWeeks;
    }
}
