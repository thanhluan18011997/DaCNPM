package server.unigo.map;

import org.mapstruct.Mapper;
import server.unigo.dto.StudyResultsDTO;
import server.unigo.dto.StudyTimesDTO;
import server.unigo.model.StudyTimes;

@Mapper
public interface StudyTimeMapper {
    StudyTimes mapDTOtoEntity(StudyTimesDTO studyTimesDTO);
    StudyTimesDTO mapEntityToDTo(StudyTimes studentTime);
}
