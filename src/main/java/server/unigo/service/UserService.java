package server.unigo.service;

import org.springframework.security.core.Authentication;
import server.unigo.dto.UsersDTO;

public interface UserService {
    public UsersDTO createUser(UsersDTO usersDTO);
    public UsersDTO getUser(String Username);
    public Boolean updateUser(UsersDTO usersDTO);
    public Authentication authentication(UsersDTO usersDTO);
}
