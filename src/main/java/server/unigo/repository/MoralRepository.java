package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.unigo.model.Morals;

import java.util.Optional;
@Repository
@Transactional
public interface MoralRepository extends JpaRepository<Morals,Long> {
    @Query(value = "SELECT m FROM Morals m where m.personalInformation.studentId=:id and m.semester=:semester")
 Optional<Morals> findBySemesterAndPersonalInformationId(@Param("id") String id,@Param("semester") String semester);
}
