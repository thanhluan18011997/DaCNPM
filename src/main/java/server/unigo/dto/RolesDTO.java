package server.unigo.dto;

import lombok.Data;
import server.unigo.model.Permissions;

import java.util.Set;

@Data
public class RolesDTO {
    private String role;
    private String roleName;
    private Set<Permissions> permissions;
}
