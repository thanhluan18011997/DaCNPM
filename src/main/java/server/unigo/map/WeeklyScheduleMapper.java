package server.unigo.map;

import org.mapstruct.Mapper;
import server.unigo.dto.WeeklySchedulesDTO;
import server.unigo.model.WeeklySchedules;

@Mapper
public interface WeeklyScheduleMapper {
    WeeklySchedules mapDTOtoEntity(WeeklySchedulesDTO weeklySchedulesDTO);
    WeeklySchedulesDTO mapEntityToDTo(WeeklySchedules weeklySchedules);
}
