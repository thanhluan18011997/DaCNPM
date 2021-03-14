package server.unigo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class SchedulesDTO {
    @JsonProperty("index")
    Long index_;
    @JsonProperty("course_code")
    String courseCode;
    @JsonProperty("course_name")
    String courseName;
    Double credit;
    String teacher;
    @JsonProperty("study_weeks")
    String studyWeeks;
    @JsonProperty("weekly_schedule")
    WeeklySchedulesDTO weeklySchedule;
}
