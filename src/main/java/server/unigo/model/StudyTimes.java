package server.unigo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class StudyTimes extends BaseEntity {
    private Long time;
    @ManyToOne
    private DetailSchedules detailSchedule;
}
