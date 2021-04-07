package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.unigo.model.StudyTimes;

import java.util.Optional;

@Repository
@Transactional
public interface StudyTimeRepository extends JpaRepository<StudyTimes,Long> {
    @Query(value = "select s.id from StudyTimes s where s.detailSchedule.id=:id")
    Optional<Long> findIdByDetailSchedule(@Param("id") Long detailScheduleId);
    @Modifying
    @Transactional
    @Query(value = "delete from StudyTimes s where s.detailSchedule.id=:id ")
    void deleteIdByDetailSchedule(@Param("id") Long WeeklyScheduleId);
}
