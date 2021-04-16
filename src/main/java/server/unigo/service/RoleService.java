package server.unigo.service;

import server.unigo.dto.RolesDTO;

public interface RoleService {
    public RolesDTO createRole(RolesDTO rolesDTO);
    public RolesDTO updateRole(RolesDTO rolesDTO);
    public RolesDTO getRole(String roleName);
    public void deleteRole(String roleName);

}
