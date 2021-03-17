package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import server.unigo.dto.StudyResultsDTO;
import server.unigo.model.Morals;
import server.unigo.model.PersonalInformations;
import server.unigo.model.StudyResults;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudyResultRepository extends JpaRepository<StudyResults,Long> {
    @Query(value = "SELECT * FROM unigo.study_results where personal_information_student_id=?",nativeQuery = true)
    Optional<List<StudyResults>> findByPersonalInformationID(String id);
    @Query(value = "SELECT * FROM unigo.study_results where personal_information_student_id=?1 and course_code=?2",nativeQuery = true)
    Optional<StudyResults> findByPersonalInformationIdAndCourseCode(String id,String courseCode);
}
