




package server.unigo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class WeeklySchedulesDTO  {
    String raw;
    @JsonProperty("schedules")
    Set<DetailSchedulesDTO> detailSchedules;
//    Schedules schedule;
}