package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.unigo.model.OverallNotifications;

public interface OverallNotificationRepository extends JpaRepository<OverallNotifications,Long> {
}
