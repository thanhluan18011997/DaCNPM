package server.unigo.map;

import org.mapstruct.Mapper;
import server.unigo.dto.StudyResultsDTO;
import server.unigo.model.StudyResults;

@Mapper
public interface StudyResultMapper {
    StudyResults mapDTOtoEntity(StudyResultsDTO studyResultsDTO);
    StudyResultsDTO mapEntityToDTo(StudyResults studyResults);
}
