package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.unigo.model.StudyTimes;

import java.util.Optional;

@Repository
public interface StudyTimeRepository extends JpaRepository<StudyTimes,Long> {
    @Query(value = "select id from unigo.study_times where detail_schedule_id=? ",nativeQuery = true)
    Optional<Long> findIdByDetailSchedule(Long detailScheduleId);
    @Modifying
    @Transactional
    @Query(value = "delete study_times from unigo.study_times where detail_schedule_id=? ",nativeQuery = true)
    void deleteIdByDetailSchedule(Long WeeklyScheduleId);
}
