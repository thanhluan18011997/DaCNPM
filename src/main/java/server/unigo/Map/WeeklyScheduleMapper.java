package server.unigo.Map;

import org.mapstruct.Mapper;
import server.unigo.dto.TestsDTO;
import server.unigo.dto.WeeklySchedulesDTO;
import server.unigo.model.Tests;
import server.unigo.model.WeeklySchedules;

@Mapper
public interface WeeklyScheduleMapper {
    WeeklySchedules mapDTOtoEntity(WeeklySchedulesDTO weeklySchedulesDTO);
    WeeklySchedulesDTO mapEntityToDTo(WeeklySchedules weeklySchedules);
}
