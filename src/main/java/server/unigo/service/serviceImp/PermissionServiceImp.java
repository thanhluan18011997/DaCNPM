package server.unigo.service.serviceImp;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import server.unigo.dto.IdAndPermissionDTO;
import server.unigo.dto.PermissionsDTO;
import server.unigo.map.PermissionMapper;
import server.unigo.model.Permissions;
import server.unigo.model.PersonalInformations;
import server.unigo.model.Roles;
import server.unigo.model.Users;
import server.unigo.repository.PermissionRepository;
import server.unigo.repository.PersonalInformationRepository;
import server.unigo.repository.RoleRepository;
import server.unigo.repository.UserRepository;
import server.unigo.service.PermissionService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImp implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PersonalInformationRepository personalInformationRepository;

    @Autowired
    public PermissionServiceImp(PermissionRepository permissionRepository, UserRepository userRepository, RoleRepository roleRepository, PersonalInformationRepository personalInformationRepository) {
        this.permissionRepository = permissionRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.personalInformationRepository = personalInformationRepository;
    }

    @Override
    public Boolean modifyPermission(Set<String> permissionsDTONameSet, String id) {
        permissionsDTONameSet.add("Permission");
        PermissionMapper permissionMapper = Mappers.getMapper(PermissionMapper.class);
        Optional<Users> usersOptional = userRepository.findByUsername(id);
        if (usersOptional.isPresent()) {
            Users user = usersOptional.get();
            Set<Permissions> permissionsSet = permissionsDTONameSet.stream().filter(t -> permissionRepository.findByPermissionName(t).isPresent() && !t.equals("ADMIN")).map(t -> permissionRepository.findByPermissionName(t).get()).collect(Collectors.toSet());
            Roles roles = new Roles("ROLE_modified", "Role of " + id);
            roles.setPermissions(permissionsSet);
            roles.setUsers(Arrays.asList(user).stream().collect(Collectors.toSet()));
            roleRepository.save(roles);
            user.setRoles(Arrays.asList(roles).stream().collect(Collectors.toSet()));
            userRepository.save(user);
            return true;

        } else new ResponseStatusException(HttpStatus.NOT_FOUND, "Nott found " + id);
        return false;
    }

    @Override
    public void deletePermission(String permissionName) {
        Optional<Permissions> permission = permissionRepository.findByPermissionName(permissionName);
        if (permission.isPresent())
            permissionRepository.delete(permission.get());
        else new ResponseStatusException(HttpStatus.NOT_FOUND, "Nott found " + permissionName);
    }


    @Override
    public IdAndPermissionDTO getPermissionForUser(String id) {
        Optional<Users> usersOptional = userRepository.findByUsername(id);
        if (usersOptional.isPresent()) {
            return new IdAndPermissionDTO(usersOptional.get().getUsername(), getPermissionByID(usersOptional.get().getUsername())
                    , getImageByID(usersOptional.get().getUsername()), usersOptional.get().isBlock(), getNameByID(id));

        } else return null;

    }


    public Set<PermissionsDTO> getPermissionByID(String id) {
        PermissionMapper permissionMapper = Mappers.getMapper(PermissionMapper.class);
        Optional<Users> usersOptional = userRepository.findByUsername(id);
        if (usersOptional.isPresent()) {
            Set<PermissionsDTO> permissionsDTOSet = usersOptional.get().getRoles().stream().findFirst().get().getPermissions()
                    .stream().map(t -> permissionMapper.mapEntityToDTo(t)).collect(Collectors.toSet());

            Set<PermissionsDTO> permissionsDTOSet1 = permissionsDTOSet.stream().filter(x -> (x.getPermissionName().equals("Điểm")
                            || (x.getPermissionName().equals("Lịch học"))
                            || (x.getPermissionName().equals("Lịch thi"))
                            || (x.getPermissionName().equals("KQ HTRL"))
                            || (x.getPermissionName().equals("collaborator"))
                            || (x.getPermissionName().equals("semester"))
                            || (x.getPermissionName().equals("class"))))
                    .collect(Collectors.toSet());


            return permissionsDTOSet1;
        } else new ResponseStatusException(HttpStatus.NOT_FOUND, "Nott found Id=" + id);
        return null;
    }


    @Override
    public List<IdAndPermissionDTO> getAllPermissionForUser() {
        List<IdAndPermissionDTO> idAndPermissionDTOList = userRepository.findAll().stream().filter(t -> !t.getUsername().equals("admin")).map(
                        t -> new IdAndPermissionDTO(t.getUsername(), getPermissionByID(t.getUsername()), getImageByID(t.getUsername()), t.isBlock(), getNameByID(t.getUsername())))
                .collect(Collectors.toList());
        return idAndPermissionDTOList;

    }

    public String getImageByID(String id) {
        try {
            return personalInformationRepository.findByStudentId(id).get().getPersonalImage();
        } catch (Exception e) {
            return "";
        }
    }

    public String getNameByID(String id) {
        try {
            return personalInformationRepository.findByStudentId(id).get().getStudentName();
        } catch (Exception e) {
            return "";
        }
    }

}
