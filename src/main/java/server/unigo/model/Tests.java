package server.unigo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Tests extends BaseEntity {

    private Long index_;
    private String courseCode;
    private String courseName;
    private String testGroup;
    private String testGrouping;
    private String testSchedule;
    @ManyToOne()
    private PersonalInformations personalInformation;
}
