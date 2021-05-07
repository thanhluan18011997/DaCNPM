package server.unigo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.unigo.dto.IdAndPermissionDTO;
import server.unigo.dto.PermissionsDTO;
import server.unigo.service.PermissionService;

import java.util.List;
import java.util.Set;

@RestController
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }
    @PreAuthorize("hasAnyAuthority('ADMIN_Authority')")
    @GetMapping("/v1/permissions/{id}")
    public Set<PermissionsDTO> getAllPermission(@PathVariable("id") String id) {
        return permissionService.getPermissionByID(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_Authority')")
    @GetMapping("/v1/permissions")
    public List<IdAndPermissionDTO> getAllPermissionUSer(){
        return permissionService.getAllPermissionForUser();
    }
    @PreAuthorize("hasAnyAuthority('ADMIN_Authority')")
    @PutMapping("/v1/permissions/{id}")
    public Boolean modifiPermission(@RequestBody Set<String> PermissionNameList, @PathVariable("id") String id) {
        return permissionService.modifyPermission(PermissionNameList, id);
    }
}
