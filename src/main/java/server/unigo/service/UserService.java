package server.unigo.service;

import org.springframework.security.core.Authentication;
import server.unigo.dto.RegisterResponseDTO;
import server.unigo.dto.UsersDTO;
import server.unigo.model.Users;

import java.util.List;

public interface UserService {
    public Users createUser(UsersDTO usersDTO);
   public RegisterResponseDTO verifyUser(UsersDTO usersDTO);
    public Authentication authentication(UsersDTO usersDTO);
    public Users setBlock(String username,boolean status);
    public UsersDTO checkBlock(String username);
    public List<UsersDTO> getAllBlockUser();

}
