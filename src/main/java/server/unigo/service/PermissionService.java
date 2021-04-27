package server.unigo.service;

import server.unigo.dto.PermissionsDTO;

import java.util.Set;

public interface PermissionService {
    public Boolean modifyPermission(Set<String > permissionsDTONameSet, String id);
    public void deletePermission(String permissionName);
    public Set<PermissionsDTO> getPermissionByID(String id);
    }
