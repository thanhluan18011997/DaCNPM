package server.unigo.service;

import org.springframework.security.core.Authentication;
import server.unigo.dto.RegisterResponseDTO;
import server.unigo.dto.UsersDTO;
import server.unigo.model.Users;

public interface UserService {
    public Users createUser(UsersDTO usersDTO);
   public RegisterResponseDTO verifyUser(UsersDTO usersDTO);
    public Authentication authentication(UsersDTO usersDTO);
}
