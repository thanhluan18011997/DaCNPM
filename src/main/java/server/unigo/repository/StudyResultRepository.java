package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.unigo.dto.StudyResultsDTO;
import server.unigo.model.Morals;
import server.unigo.model.PersonalInformations;
import server.unigo.model.StudyResults;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface StudyResultRepository extends JpaRepository<StudyResults,Long> {
    @Query(value = "SELECT s FROM StudyResults s where s.personalInformation.studentId=:studentId")
    Optional<List<StudyResults>> findByPersonalInformationID(@Param("studentId") String studentId);
    @Query(value = "SELECT s FROM StudyResults s where s.personalInformation.studentId=:studentId and s.courseCode=:courseCode")
    Optional<StudyResults> findByPersonalInformationIdAndCourseCode(@Param("studentId")String id,@Param("courseCode") String courseCode);
    @Query(value = "SELECT s FROM StudyResults s where s.personalInformation.studentId=:studentId and lower(s.courseName) LIKE lower (CONCAT('%',:courseName,'%'))")
    Optional<List<StudyResults>> findByIdAndCourseNameContaining(@Param("studentId")String id,@Param("courseName") String courseName);
}
