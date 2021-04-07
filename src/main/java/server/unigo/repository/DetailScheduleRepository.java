package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.unigo.model.DetailSchedules;
import server.unigo.model.Notifications;
import server.unigo.model.WeeklySchedules;

import java.util.Optional;

@Repository
@Transactional
public interface DetailScheduleRepository extends JpaRepository<DetailSchedules,Long> {
    @Query(value = "select d.id from DetailSchedules d where d.weeklySchedule.id=:id ")
    Optional<Long> findIdByWeeklyScheduleId(@Param("id") Long WeeklyScheduleId);
    @Modifying
    @Transactional
    @Query(value = "delete from DetailSchedules d where d.weeklySchedule.id=:id " )
    void deleteByWeeklyScheduleId(@Param("id") Long WeeklyScheduleId);
}
