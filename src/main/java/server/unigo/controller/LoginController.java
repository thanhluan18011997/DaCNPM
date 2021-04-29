package server.unigo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import server.unigo.dto.RegisterResponseDTO;
import server.unigo.dto.UsersDTO;
import server.unigo.model.Users;
import server.unigo.repository.UserRepository;
import server.unigo.security.CustomUserDetail;
import server.unigo.security.JwtProvider;
import server.unigo.security.LoginOutput;
import server.unigo.service.UserService;

import java.util.Base64;
@Log4j2
@RestController
public class LoginController {
    @Autowired
    private JwtProvider tokenProvider;
    @Autowired
    private UserService userService;

    @PostMapping("/v1/login")
    public LoginOutput authenticateUser( @RequestBody UsersDTO usersDTO) {
        log.info("User with ID="+usersDTO.getUsername()+" logining");
       try {

               Authentication authentication=userService.authentication(usersDTO);
               String jwt = tokenProvider.generateJwt((CustomUserDetail) authentication.getPrincipal());
               return new LoginOutput(jwt);


       }
       catch(Exception e){
           Users user = userService.createUser(usersDTO);
           if (user!=null){
               String jwt = tokenProvider.generateJwt(new CustomUserDetail(user));
               return new LoginOutput(jwt);
           }
           else{
               LoginOutput loginOutput = new LoginOutput("");
               loginOutput.setStatus("Invalid");
               return loginOutput;
           }

       }

    }
}