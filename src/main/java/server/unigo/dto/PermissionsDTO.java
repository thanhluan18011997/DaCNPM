package server.unigo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.unigo.model.BaseEntity;
import server.unigo.model.Roles;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
public class PermissionsDTO  {
    private String permission;
    private String permissionName;
    private String name;
    private String imageUrl;
    private boolean block;
}