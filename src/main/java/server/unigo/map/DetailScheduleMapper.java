package server.unigo.map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import server.unigo.dto.DetailSchedulesDTO;
import server.unigo.model.DetailSchedules;
@Mapper(uses = {StudyTimeMapper.class})
public interface DetailScheduleMapper {
    DetailSchedules mapDTOtoEntity(DetailSchedulesDTO detailSchedulesDTO);
    @Mapping(source = "studyTime", target = "studyTime")
    DetailSchedulesDTO mapEntityToDTo(DetailSchedules detailSchedules);
}
