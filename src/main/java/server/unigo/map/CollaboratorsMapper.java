package server.unigo.map;

import org.mapstruct.Mapper;
import server.unigo.dto.CollaboratorDTO;
import server.unigo.dto.UsersDTO;
import server.unigo.model.Collaborators;
import server.unigo.model.Users;

@Mapper
public interface CollaboratorsMapper {
    Collaborators mapDTOtoEntity(CollaboratorDTO collaboratorDTO);
    CollaboratorDTO mapEntityToDTo(Collaborators collaborators);
}
