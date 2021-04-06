package server.unigo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class DetailSchedules extends BaseEntity {
    Long weekday;
    String room;
    @ManyToOne
    WeeklySchedules weeklySchedule;
    @OneToMany(mappedBy = "detailSchedule",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    Set<StudyTimes> studyTime;
}
