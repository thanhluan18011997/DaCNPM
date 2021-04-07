package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.unigo.model.PersonalInformations;
import server.unigo.model.Schedules;
import server.unigo.model.StudyResults;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ScheduleRepository extends JpaRepository<Schedules,Long> {
    @Query(value = "select s.id from Schedules s  where s.personalInformation.studentId=:studentId and  s.courseCode=:courseCode")
    Optional<Long> findScheduleIdByPersonalInformationAndCourseName(@Param("studentId") String personal_information_student_id,@Param("courseCode") String courseCode);
    @Query(value = "select s from Schedules s  where s.personalInformation.studentId=:studentId")
    Optional<List<Schedules>> findByPersonalInformationID(@Param("studentId") String personal_information_student_id);
}
