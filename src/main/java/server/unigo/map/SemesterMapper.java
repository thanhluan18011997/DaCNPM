package server.unigo.map;

import org.mapstruct.Mapper;
import server.unigo.dto.SemesterDTO;
import server.unigo.dto.UsersDTO;
import server.unigo.model.Semesters;
import server.unigo.model.Users;

@Mapper
public interface SemesterMapper {
    Semesters mapDTOtoEntity(SemesterDTO semesterDTO);
    SemesterDTO mapEntityToDTo(Semesters semesters);
}
