package server.unigo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
    @ElementCollection
    Set<Long> studyTime;
}
