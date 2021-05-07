package server.unigo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
public class IdAndPermissionDTO {
    private String id ;
    private Set<PermissionsDTO> permissionsDTOSet;
}
