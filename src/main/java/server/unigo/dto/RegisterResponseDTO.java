package server.unigo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterResponseDTO {
    private boolean status;
    private String sess_id;
}
