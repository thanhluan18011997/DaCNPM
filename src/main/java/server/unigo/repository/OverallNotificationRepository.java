package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.unigo.model.OverallNotifications;

import java.util.Optional;

public interface OverallNotificationRepository extends JpaRepository<OverallNotifications,Long> {
    Optional<OverallNotifications> findByTitle(String title);

}
