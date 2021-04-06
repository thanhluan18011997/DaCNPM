package server.unigo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.unigo.dto.WeeklySchedulesDTO;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Schedules extends BaseEntity {
    Long index_;
    String courseCode;
    String courseName;
    Double credit;
    String teacher;
    String studyWeeks;
    @ManyToOne()
    PersonalInformations personalInformation;
    @OneToOne(mappedBy = "schedules",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    WeeklySchedules weeklySchedules;
}
