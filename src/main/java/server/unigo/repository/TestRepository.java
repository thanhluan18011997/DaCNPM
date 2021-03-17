package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import server.unigo.model.Morals;
import server.unigo.model.StudyResults;
import server.unigo.model.Tests;

import java.util.List;
import java.util.Optional;

public interface TestRepository extends JpaRepository<Tests,Long> {
    @Query(value = "SELECT * FROM unigo.tests where personal_information_student_id=?",nativeQuery = true)
    Optional<List<Tests>> findByPersonalInformationID(String id);
    @Query(value = "SELECT * FROM unigo.tests where personal_information_student_id=?1 and course_code=?2",nativeQuery = true)
    Optional<Tests> findByPersonalInformationIdAndCourseCode(String id,String courseCode);
}
