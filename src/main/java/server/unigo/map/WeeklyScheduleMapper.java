package server.unigo.map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import server.unigo.dto.WeeklySchedulesDTO;
import server.unigo.model.WeeklySchedules;

@Mapper(uses = {DetailScheduleMapper.class})
public interface WeeklyScheduleMapper {
    WeeklySchedules mapDTOtoEntity(WeeklySchedulesDTO weeklySchedulesDTO);
    @Mapping(source = "detailSchedules",target = "detailSchedules")
    WeeklySchedulesDTO mapEntityToDTo(WeeklySchedules weeklySchedules);
}
