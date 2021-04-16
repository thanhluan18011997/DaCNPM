package server.unigo.service;

import server.unigo.dto.PermissionsDTO;

public interface PermissionService {
    public PermissionsDTO createPermission(PermissionsDTO permissionsDTO);
    public void deletePermission(String permissionName);
    public PermissionsDTO getPermission(String permissionName);
    }
