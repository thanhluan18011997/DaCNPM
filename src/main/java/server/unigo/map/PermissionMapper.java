package server.unigo.map;

import org.mapstruct.Mapper;
import server.unigo.dto.PermissionsDTO;
import server.unigo.model.Permissions;

@Mapper
public interface PermissionMapper {
    Permissions mapDTOtoEntity(PermissionsDTO permissionsDTO);
    PermissionsDTO mapEntityToDTo(Permissions permissions);
}
