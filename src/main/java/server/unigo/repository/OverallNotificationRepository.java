package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.unigo.model.OverallNotifications;

import java.util.Optional;
@Repository
@Transactional
public interface OverallNotificationRepository extends JpaRepository<OverallNotifications,Long> {
    Optional<OverallNotifications> findByTitle(String title);

}
