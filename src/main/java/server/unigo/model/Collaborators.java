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
public class Collaborators extends BaseEntity {
    private String sutdent_id;
    private String student_name;
    private String class_name;
    private String phone;
    @ManyToMany(mappedBy = "collaborators",fetch = FetchType.LAZY)
    private Set<Schedules>schedules;
    public Collaborators(String sutdent_id,String student_name,String class_name,String phone){
        this.sutdent_id=sutdent_id;
        this.student_name=student_name;
        this.class_name=class_name;
        this.phone=phone;
    }
}
