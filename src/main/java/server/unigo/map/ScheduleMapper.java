package server.unigo.map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import server.unigo.dto.DetailSchedulesDTO;
import server.unigo.dto.SchedulesDTO;
import server.unigo.dto.StudyTimesDTO;
import server.unigo.dto.WeeklySchedulesDTO;
import server.unigo.model.DetailSchedules;
import server.unigo.model.Schedules;
import server.unigo.model.StudyTimes;
import server.unigo.model.WeeklySchedules;

@Mapper
public interface ScheduleMapper {
    Schedules mapDTOtoEntity(SchedulesDTO schedulesDTO);
    @Mapping(source = "weeklySchedules",target = "weeklySchedule")
    SchedulesDTO mapEntityToDTo(Schedules schedules);


}
