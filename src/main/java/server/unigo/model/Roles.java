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
public class Roles extends BaseEntity {
    private String roleName;
    private String role;
    @ManyToMany(mappedBy ="roles",fetch = FetchType.LAZY)
    private Set<Users> users;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_permission",joinColumns = @JoinColumn(name="id_role"),inverseJoinColumns = @JoinColumn(name="id_permission"))
    private Set<Permissions> permissions;
    public Roles (String role,String roleName){
        this.role=role;
        this.roleName=roleName;
    }
}