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
    private Long index_;
    private String courseCode;
    private String courseName;
    private Double credit;
    private String teacher;
    private String studyWeeks;
    @ManyToOne()
    private PersonalInformations personalInformation;
    @OneToOne(mappedBy = "schedules",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private WeeklySchedules weeklySchedules;
}
