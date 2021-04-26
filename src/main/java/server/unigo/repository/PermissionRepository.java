package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.unigo.model.Permissions;
import server.unigo.model.Roles;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PermissionRepository extends JpaRepository<Permissions,Long> {
    Optional<Permissions> findByPermission(String permission);
    Optional<Permissions> findByPermissionName(String permissionName);

}
