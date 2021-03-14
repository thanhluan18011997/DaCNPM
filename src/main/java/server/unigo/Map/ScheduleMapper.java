package server.unigo.Map;

import org.mapstruct.Mapper;
import server.unigo.dto.SchedulesDTO;
import server.unigo.model.Schedules;

@Mapper
public interface ScheduleMapper {
    Schedules mapDTOtoEntity(SchedulesDTO schedulesDTO);
    SchedulesDTO mapEntityToDTo(Schedules schedules);
}
