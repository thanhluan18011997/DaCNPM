package server.unigo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import server.unigo.model.Roles;

import java.util.Set;

@Data
public class UsersDTO {
    private String username;
    private String password;
}
