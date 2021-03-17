package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import server.unigo.model.Morals;

import java.util.List;
import java.util.Optional;

public interface MoralRepository extends JpaRepository<Morals,Long> {
    @Query(value = "SELECT * FROM unigo.morals where personal_information_student_id=?1 and semester=?2",nativeQuery = true)
 Optional<Morals> findBySemesterAndPersonalInformationId(String id,String semester);
}
