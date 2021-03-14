package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.unigo.model.WeeklySchedules;

@Repository
public interface WeeklySchedulesRepository extends JpaRepository<WeeklySchedules,Long> {
}
