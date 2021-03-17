package server.unigo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Data
@AllArgsConstructor
public class StudyTimesDTO {
    Long time;
}
