package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.unigo.model.DetailSchedules;
import server.unigo.model.Users;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<Users,Long> {
   Optional<Users>  findByUsername(String username);
   Optional<Users>  findById(Long id);

}
