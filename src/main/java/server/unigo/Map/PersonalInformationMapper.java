package server.unigo.Map;

import org.mapstruct.Mapper;
import server.unigo.dto.MoralsDTO;
import server.unigo.dto.PersonalInformationsDTO;
import server.unigo.dto.WeeklySchedulesDTO;
import server.unigo.model.Morals;
import server.unigo.model.PersonalInformations;
import server.unigo.model.WeeklySchedules;

@Mapper
public interface PersonalInformationMapper {
    PersonalInformations mapDTOtoEntity(PersonalInformationsDTO personalInformationsDTO);
    PersonalInformationsDTO mapEntityToDTo(PersonalInformations personalInformations);
}
