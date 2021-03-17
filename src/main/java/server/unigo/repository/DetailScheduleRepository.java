package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.unigo.model.DetailSchedules;
import server.unigo.model.Notifications;
import server.unigo.model.WeeklySchedules;

import java.util.Optional;

@Repository
public interface DetailScheduleRepository extends JpaRepository<DetailSchedules,Long> {
    @Query(value = "select id from unigo.detail_schedules where weekly_schedule_id=? ",nativeQuery = true)
    Optional<Long> findIdByWeeklyScheduleId(Long WeeklyScheduleId);
    @Modifying
    @Transactional
    @Query(value = "delete detail_schedules from unigo.detail_schedules where weekly_schedule_id=? ",nativeQuery = true)
    void deleteByWeeklyScheduleId(Long WeeklyScheduleId);
}
