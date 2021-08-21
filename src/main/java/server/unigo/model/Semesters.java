package server.unigo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.unigo.dto.WeeklySchedulesDTO;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Semesters extends BaseEntity {
    private String semester_id;
    private String semester_name;
    @OneToMany(mappedBy = "semester",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Schedules> schedulesSet;

}
