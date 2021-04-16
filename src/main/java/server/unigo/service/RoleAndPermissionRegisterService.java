package server.unigo.service;

import server.unigo.model.Permissions;
import server.unigo.model.Roles;

public interface RoleAndPermissionRegisterService {
    public Roles createRole(String roleName);
    public Permissions createPermission(String permissionName);
    public Roles addPermissionForRole(String roleName,String Permission);
    public Roles removePermissionForRole(String roleName,String Permission);
    public Roles addRoleForUser(String roleName,String username);
    public Roles removeRoleForUser(String roleName,String username);
    public  Permissions addPermissionForUser(String permissionName,String roleName,String username);
    public  Permissions removePermissionForUser(String permissionName,String roleName,String username);


}
