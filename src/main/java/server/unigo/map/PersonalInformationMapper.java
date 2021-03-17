package server.unigo.map;

import org.mapstruct.Mapper;
import server.unigo.dto.PersonalInformationsDTO;
import server.unigo.model.PersonalInformations;

@Mapper
public interface PersonalInformationMapper {
    PersonalInformations mapDTOtoEntity(PersonalInformationsDTO personalInformationsDTO);
    PersonalInformationsDTO mapEntityToDTo(PersonalInformations personalInformations);
}
