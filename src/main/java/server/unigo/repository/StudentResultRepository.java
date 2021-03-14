package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.unigo.model.Morals;
import server.unigo.model.StudyResults;

@Repository
public interface StudentResultRepository extends JpaRepository<StudyResults,Long> {
}
