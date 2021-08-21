package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.unigo.model.Collaborators;
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

    @Query(value = "select s from Schedules s  where s.personalInformation.studentId=:studentId and s.semester.semester_id=:semesterId")
    Optional<List<Schedules>> findByPersonalInformationIDAndSemester(@Param("studentId") String personal_information_student_id,@Param("semesterId") String semesterId);


    @Query(value = "select s from Schedules s  where s.personalInformation.studentId=:studentId and s.courseName LIKE lower (CONCAT('%',:name,'%'))")
    Optional<List<Schedules>> findByPersonalInformationIDAndName(@Param("studentId") String personal_information_student_id,@Param("name") String name);

    @Query(value = "select s.collaborators from Schedules s  where s.courseCode=:courseCode")
    Optional<List<Collaborators>> findCollaboratorByCoursecode(@Param("courseCode") String courseCode);
}
