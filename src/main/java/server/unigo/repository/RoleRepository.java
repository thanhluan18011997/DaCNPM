package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.unigo.model.Roles;
import server.unigo.model.Users;

import java.util.Optional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Roles,Long> {
    Optional<Roles> findByRole(String role);
    Optional<Roles> findByRoleName(String roleName);

}
