package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import server.unigo.model.PersonalInformations;
import server.unigo.model.Schedules;
import server.unigo.model.StudyResults;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedules,Long> {
    @Query(value = "select id from schedules as s where s.personal_information_student_id=?1 and  s.course_code=?2", nativeQuery = true)
    Optional<Long> findScheduleIdByPersonalInformationAndCourseName(String personal_information_student_id, String courseCode);
    @Query(value = "SELECT * FROM unigo.schedules where personal_information_student_id=?",nativeQuery = true)
    Optional<List<Schedules>> findByPersonalInformationID(String id);
}
