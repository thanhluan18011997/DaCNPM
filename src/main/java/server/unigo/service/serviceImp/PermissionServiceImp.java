package server.unigo.service.serviceImp;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import server.unigo.dto.PermissionsDTO;
import server.unigo.map.PermissionMapper;
import server.unigo.model.Permissions;
import server.unigo.repository.PermissionRepository;
import server.unigo.service.PermissionService;

import java.util.Optional;

@Service
public class PermissionServiceImp implements PermissionService {
    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionServiceImp(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public PermissionsDTO createPermission(PermissionsDTO permissionsDTO) {
        PermissionMapper permissionMapper = Mappers.getMapper(PermissionMapper.class);
        return permissionMapper.mapEntityToDTo(permissionRepository.save(permissionMapper.mapDTOtoEntity(permissionsDTO)));
    }

    @Override
    public void deletePermission(String permissionName) {
        Optional<Permissions> permission = permissionRepository.findByPermissionName(permissionName);
        if (permission.isPresent())
            permissionRepository.delete(permission.get());
        else new ResponseStatusException(HttpStatus.NOT_FOUND, "Nott found " + permissionName);
    }


    @Override
    public PermissionsDTO getPermission(String permissionName) {
        PermissionMapper permissionMapper = Mappers.getMapper(PermissionMapper.class);
        Optional<Permissions> permission = permissionRepository.findByPermissionName(permissionName);
        if (permission.isPresent())
            return permissionMapper.mapEntityToDTo(permission.get());
        else new ResponseStatusException(HttpStatus.NOT_FOUND, "Nott found " + permissionName);
        return null;
    }
}
