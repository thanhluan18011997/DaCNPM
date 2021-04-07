package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.unigo.model.Morals;
import server.unigo.model.StudyResults;
import server.unigo.model.Tests;

import java.util.List;
import java.util.Optional;
@Repository
@Transactional
public interface TestRepository extends JpaRepository<Tests,Long> {
    @Query(value = "SELECT t FROM Tests t where t.personalInformation.studentId=:id")
    Optional<List<Tests>> findByPersonalInformationID(@Param("id") String id);
    @Query(value = "SELECT t FROM Tests t where t.personalInformation.studentId=:id and t.courseCode=:courseCode")
    Optional<Tests> findByPersonalInformationIdAndCourseCode(@Param("id") String id,@Param("courseCode") String courseCode);
}
