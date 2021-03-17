package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import server.unigo.model.Schedules;
import server.unigo.model.WeeklySchedules;

import java.util.Optional;

@Repository
public interface WeeklySchedulesRepository extends JpaRepository<WeeklySchedules,Long> {
    @Query(value = "SELECT id FROM unigo.weekly_schedules where schedules_id=?",nativeQuery = true)
    Optional<Long> findIdBySchedulesId(Long scheduleId);
}
