package server.unigo.service.serviceImp;

import lombok.extern.log4j.Log4j2;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import server.unigo.dto.RegisterResponseDTO;
import server.unigo.dto.UsersDTO;
import server.unigo.map.UserMapper;
import server.unigo.model.Users;
import server.unigo.repository.RoleRepository;
import server.unigo.repository.UserRepository;
import server.unigo.service.*;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class UserServiceImp implements UserService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final PersonalInformationService personalInformationService;
    private final MoralService moralService;
    private final ScheduleService scheduleService;
    private final StudyResultService studyResultService;
    private final TestService testService;


    @Autowired
    public UserServiceImp(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, RestTemplate restTemplate, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, PersonalInformationService personalInformationService, MoralService moralService, ScheduleService scheduleService, StudyResultService studyResultService, TestService testService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.personalInformationService = personalInformationService;
        this.moralService = moralService;
        this.scheduleService = scheduleService;
        this.studyResultService = studyResultService;
        this.testService = testService;
    }

    @Override
    public Users createUser(UsersDTO usersDTO) {
        RegisterResponseDTO registerResponseDTO = verifyUser(usersDTO);
        UserMapper userMapper = Mappers.getMapper(UserMapper.class);
        Optional<Users> usersOptional = userRepository.findByUsername(usersDTO.getUsername());
        if (registerResponseDTO.isStatus()) {
            Users user = userMapper.mapDTOtoEntity(usersDTO);
            user.setRoles(Arrays.asList(roleRepository.findByRole("ROLE_USER").get()).stream().collect(Collectors.toSet()));
            user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
            if (usersOptional.isPresent()) {
                user.setId(usersOptional.get().getId());
            } else {
                Users saveUsers = userRepository.save(user);
                personalInformationService.savePersonalInformation(user.getUsername());
                moralService.saveMoral(user.getUsername());
                scheduleService.saveSchedule(user.getUsername());
                studyResultService.saveStudentResult(user.getUsername());
                testService.saveTest(user.getUsername());


            }
            return userRepository.save(user);
        } else new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or Password Invalid");
        return null;
    }

    @Override
    public RegisterResponseDTO verifyUser(UsersDTO usersDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<RegisterResponseDTO> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://dnunigo.herokuapp.com/dut/")
                .queryParam("command", "register")
                .queryParam("username", usersDTO.getUsername())
                .queryParam("password", usersDTO.getPassword());
        ResponseEntity<RegisterResponseDTO> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, RegisterResponseDTO.class);
        return responseEntity.getBody();
    }


    @Override
    public Authentication authentication(UsersDTO usersDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usersDTO.getUsername(),
                        Base64.getEncoder().encodeToString(usersDTO.getPassword().getBytes()), userDetailsService.loadUserByUsername(usersDTO.getUsername()).getAuthorities()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    @Override
    public Users setBlock(String username,boolean status) {
        Optional<Users> usersOptional = userRepository.findByUsername(username);
        if (usersOptional.isPresent()) {
            Users users = usersOptional.get();
            users.setBlock(status);
          return  userRepository.save(users);
        }
        return null;
    }

    @Override
    public UsersDTO checkBlock(String username) {
        UserMapper userMapper= Mappers.getMapper(UserMapper.class);
        Users users = userRepository.findByUsername(username).get();
        return userMapper.mapEntityToDTo(users);

    }

    @Override
    public List<UsersDTO> getAllBlockUser() {
        List<Users> usersList=userRepository.findAll().stream().filter(t->t.isBlock()).collect(Collectors.toList());
        UserMapper userMapper=Mappers.getMapper(UserMapper.class);
        return usersList.stream().map(t->userMapper.mapEntityToDTo(t)).collect(Collectors.toList());
    }
}
