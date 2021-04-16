package server.unigo.map;

import org.mapstruct.Mapper;
import server.unigo.dto.RolesDTO;
import server.unigo.model.Roles;

@Mapper
public interface RoleMapper {
    Roles mapDTOtoEntity(RolesDTO rolesDTO);
    RolesDTO mapEntityToDTo(Roles roles);
}
