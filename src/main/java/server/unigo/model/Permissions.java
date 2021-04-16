package server.unigo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Permissions extends BaseEntity {

    private String permissionName;
    private String permission;
    @ManyToMany(mappedBy = "permissions",fetch = FetchType.LAZY)
    private Set<Roles>roles;
    public Permissions (String permission,String permissionName){
        this.permission=permission;
        this.permissionName=permissionName;
    }

}