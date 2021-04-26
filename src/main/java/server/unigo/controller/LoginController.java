package server.unigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.dto.RegisterResponseDTO;
import server.unigo.dto.UsersDTO;
import server.unigo.model.Users;
import server.unigo.security.CustomUserDetail;
import server.unigo.security.JwtProvider;
import server.unigo.security.LoginOutput;
import server.unigo.service.UserService;

@RestController
public class LoginController {
    @Autowired
    private JwtProvider tokenProvider;
    @Autowired
    private UserService userService;
    @PostMapping("/v1/login")
    public LoginOutput authenticateUser( @RequestBody UsersDTO usersDTO) {

       try {
           if (userService.verifyUser(usersDTO).isStatus()){
               Authentication authentication=userService.authentication(usersDTO);
               String jwt = tokenProvider.generateJwt((CustomUserDetail) authentication.getPrincipal());
               return new LoginOutput(jwt);
           }

       }
       catch(Exception e){
           Users user = userService.createUser(usersDTO);
           if (user!=null){
               String jwt = tokenProvider.generateJwt(new CustomUserDetail(user));
               return new LoginOutput(jwt);
           }
       }
        return null;
    }
}
