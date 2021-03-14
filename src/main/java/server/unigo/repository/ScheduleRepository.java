package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.unigo.model.Schedules;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedules,Long> {
}
