package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.unigo.model.DetailSchedules;
import server.unigo.model.Notifications;

@Repository
public interface DetailScheduleRepository extends JpaRepository<DetailSchedules,Long> {
}
