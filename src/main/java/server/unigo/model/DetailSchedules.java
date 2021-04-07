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
    private Long weekday;
    private String room;
    @ManyToOne
    private WeeklySchedules weeklySchedule;
    @OneToMany(mappedBy = "detailSchedule",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<StudyTimes> studyTime;
}
