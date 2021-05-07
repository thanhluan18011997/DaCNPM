package server.unigo.dataSeeding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import server.unigo.model.Permissions;
import server.unigo.model.Roles;
import server.unigo.model.Users;
import server.unigo.repository.PermissionRepository;
import server.unigo.repository.RoleRepository;
import server.unigo.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class RoleAndPermissionSeeding implements ApplicationListener {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;


    public void createRolesAndPermission(List<String> roles, List<String> permissions) {
        Set<Permissions> permissionsSet = permissions.stream().map(t -> new Permissions(t, t.split("_")[1])).collect(Collectors.toSet());
        permissionsSet.forEach(t -> permissionRepository.save(t));
        Set<Roles> rolesSet = roles.stream().map(t -> new Roles(t, t.split("_")[1])).collect(Collectors.toSet());
        rolesSet.forEach(t -> {
            roleRepository.save(t);
            t.setPermissions(permissionsSet);
            roleRepository.save(t);
        });
        permissionsSet.forEach(t -> {
            t.setRoles(rolesSet);
            permissionRepository.save(t);
        });
    }

    //Create data
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        Long permissionsTotal = permissionRepository.count();
        Long roleTotal = roleRepository.count();
        if (permissionsTotal == 0 && roleTotal == 0) {
            createRolesAndPermission(Arrays.asList("ROLE_ADMIN", "ROLE_USER"), Arrays.asList("READ_Moral",
                     "READ_PersonalInformation", "READ_Schedule", "READ_StudyResult", "READ_Test"));
        }
        Optional<Users> admin = userRepository.findByUsername("admin");
        if (!admin.isPresent()) {
            Users ad = new Users();
            ad.setUsername("admin");
            ad.setPassword(Base64.getEncoder().encodeToString("123456".getBytes()));
            Roles adminRole = roleRepository.findByRole("ROLE_ADMIN").get();
            Set<Permissions> permissionsSet = permissionRepository.findAll().stream().collect(Collectors.toSet());
            Permissions permissions = new Permissions("ADMIN_Authority", "ADMIN");
            permissionsSet.add(permissionRepository.save(permissions));
            adminRole.setPermissions(permissionsSet);
            roleRepository.save(adminRole);
            permissions.setRoles(Arrays.asList(adminRole).stream().collect(Collectors.toSet()));
            permissionsSet.add(permissionRepository.save(permissions));
            adminRole.setUsers(Arrays.asList(ad).stream().collect(Collectors.toSet()));
            ad.setRoles(roleRepository.findAll().stream().collect(Collectors.toSet()));
            userRepository.save(ad);
        }


    }
}
