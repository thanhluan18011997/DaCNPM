package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.unigo.model.Friends;
import server.unigo.model.Semesters;
import server.unigo.model.Tests;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface FriendRepository extends JpaRepository<Friends,Long> {
    @Query(value = "SELECT t FROM Friends t where t.personalInformation.studentId=:id")
    Optional<List<Friends>> findByPersonalInformationID(@Param("id") String id);
    @Query(value = "SELECT t FROM Friends t where t.personalInformation.studentId=:student_id and t.sutdent_id=:friend_id")
    Optional<Friends> findByPersonalInformationIDAndFriendID(@Param("student_id") String student_id,@Param("friend_id") String friend_id);
}
