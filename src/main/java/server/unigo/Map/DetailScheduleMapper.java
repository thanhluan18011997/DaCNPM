package server.unigo.Map;

import org.mapstruct.Mapper;
import server.unigo.dto.DetailSchedulesDTO;
import server.unigo.model.DetailSchedules;
@Mapper
public interface DetailScheduleMapper {
    DetailSchedules mapDTOtoEntity(DetailSchedulesDTO detailSchedulesDTO);
    DetailSchedulesDTO mapEntityToDTo(DetailSchedules detailSchedules);
}
