package server.unigo.Map;

import org.mapstruct.Mapper;
import server.unigo.dto.MoralsDTO;
import server.unigo.dto.NotificationsDTO;
import server.unigo.dto.PersonalInformationsDTO;
import server.unigo.model.Morals;
import server.unigo.model.Notifications;
import server.unigo.model.PersonalInformations;

@Mapper
public interface MoralMapper {
    Morals mapDTOtoEntity(MoralsDTO   moralsDTO);
    MoralsDTO mapEntityToDTo(Morals morals);
}
