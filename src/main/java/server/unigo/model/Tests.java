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

    Long index_;
    String courseCode;
    String courseName;
    String testGroup;
    String testGrouping;
    String testSchedule;
    @ManyToOne()
    PersonalInformations personalInformation;
}
