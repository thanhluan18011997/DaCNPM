package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.unigo.model.Collaborators;
import server.unigo.model.Friends;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CollaboratorRepository extends JpaRepository<Collaborators,Long> {
//    @Query(value = "SELECT t FROM Friends t where t.personalInformation.studentId=:id")
//    Optional<List<Friends>> findByCourseIdAndCollabId(@Param("id") String id);
//    @Query(value = "SELECT t FROM Collaborators t where t.schedule.courseName=:courseName and t.sutdent_id=:sutdent_id")
//    Optional<Collaborators> findByCourseIdAndCollabId(@Param("courseName") String courseName,@Param("sutdent_id") String sutdent_id);
    @Query(value = "SELECT t FROM Collaborators t where t.sutdent_id=:id")
    Optional<Collaborators> findBySutdent_id(String id);
}
