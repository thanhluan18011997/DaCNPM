package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.unigo.model.PersonalInformations;

import java.util.Optional;

@Repository
public interface PersonalInformationRepository extends JpaRepository<PersonalInformations,Long> {
    public Optional<PersonalInformations> findByStudentId(String studentId);
}
