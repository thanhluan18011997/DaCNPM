package server.unigo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import server.unigo.model.WeeklySchedules;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;

@Setter
@Getter
public class DetailSchedulesDTO  {
    Long weekday;
    String room;
//    WeeklySchedules weeklySchedules;
    @JsonProperty("study_time")
    Set<Long> studyTime;
}
