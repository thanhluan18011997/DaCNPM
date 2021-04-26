package server.unigo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("v1/permissions/{id}")
    public Set<PermissionsDTO> getAllPermission(@PathVariable("id") String id) {
        return permissionService.getPermissionByID(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_Authority')")
    @PutMapping("v1/permissions/{id}")
    public void modifiPermission(@RequestBody Set<String> PermissionNames, @PathVariable("id") String id) {
        permissionService.modifyPermission(PermissionNames, id);
    }
}
