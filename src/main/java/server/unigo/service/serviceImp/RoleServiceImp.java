package server.unigo.service.serviceImp;

import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import server.unigo.dto.RolesDTO;
import server.unigo.map.RoleMapper;
import server.unigo.model.Roles;
import server.unigo.repository.RoleRepository;
import server.unigo.service.RoleService;

import java.util.Optional;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RolesDTO createRole(RolesDTO rolesDTO) {
        RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);
        return roleMapper.mapEntityToDTo(roleRepository.save(roleMapper.mapDTOtoEntity(rolesDTO)));
    }

    @Override
    public RolesDTO updateRole(RolesDTO rolesDTO) {
        RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);
        Optional<Roles> rolesOptional = roleRepository.findByRole(rolesDTO.getRole());
        if (rolesOptional.isPresent()) {
            Roles roles = roleMapper.mapDTOtoEntity(rolesDTO);
            roles.setId(roleRepository.findById(rolesOptional.get().getId()).get().getId());
            return roleMapper.mapEntityToDTo(roleRepository.save(roles));
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not founud role with roleName=" + rolesDTO.getRoleName());
    }

    @Override
    public RolesDTO getRole(String roleName) {
        Optional<Roles> rolesOptional = roleRepository.findByRole(roleName);
        RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);
        if (rolesOptional.isPresent())
            return roleMapper.mapEntityToDTo(rolesOptional.get());
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not founud role with roleName=" + roleName);
    }

    @Override
    public void deleteRole(String roleName) {
        Optional<Roles> roles = roleRepository.findByRoleName(roleName);
        if (roles.isPresent())
            roleRepository.delete(roles.get());
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not founud role with roleName=" + roleName);
    }
}
