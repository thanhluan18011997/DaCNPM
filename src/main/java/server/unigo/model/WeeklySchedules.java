




package server.unigo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.unigo.dto.SchedulesDTO;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class WeeklySchedules extends BaseEntity {
    private String raw;
    @OneToMany(mappedBy ="weeklySchedule",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<DetailSchedules> detailSchedules;
    @OneToOne
    private Schedules schedules;
}