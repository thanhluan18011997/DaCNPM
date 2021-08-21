package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.unigo.model.Roles;
import server.unigo.model.Semesters;

import java.util.Optional;

@Repository
@Transactional
public interface SemesterRepository extends JpaRepository<Semesters,Long> {

}
