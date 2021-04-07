package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.unigo.model.Schedules;
import server.unigo.model.WeeklySchedules;

import java.util.Optional;

@Repository
@Transactional
public interface WeeklySchedulesRepository extends JpaRepository<WeeklySchedules,Long> {
    @Query(value = "SELECT w.id FROM WeeklySchedules w where w.schedules.id=:id")
    Optional<Long> findIdBySchedulesId(@Param("id") Long scheduleId);
}
