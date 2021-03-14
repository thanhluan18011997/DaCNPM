package server.unigo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class PersonalInformationsDTO {
    @JsonProperty("student_name")
    String studentName;
    @JsonProperty("sutdent_id")
    String studentId;
    @JsonProperty("class_name")
    String className;
    @JsonProperty("personal_email")
    String personalEmail;
    String phone;
    String birthday;
    @JsonProperty("school_mail")
    String schoolMail;
    @JsonProperty("medical_id")
    String medicalId;
    @JsonProperty("medical_id_end")
    String medicalIdEnd;
    @JsonProperty("personal_image")
    String personalImage;
}
