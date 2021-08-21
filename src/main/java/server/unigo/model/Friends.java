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
public class Friends extends BaseEntity {
    private String sutdent_id;
    private String student_name;
    private String phone;
    private String mail;
    private String parent_phone;
    @ManyToOne()
    private PersonalInformations personalInformation;

}
