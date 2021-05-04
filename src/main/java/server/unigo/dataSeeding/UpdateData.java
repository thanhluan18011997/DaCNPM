package server.unigo.dataSeeding;

import lombok.extern.log4j.Log4j2;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import server.unigo.dto.UsersDTO;
import server.unigo.map.UserMapper;
import server.unigo.repository.MoralRepository;
import server.unigo.repository.UserRepository;
import server.unigo.service.*;

import java.util.Base64;
@Log4j2
@Component
public class UpdateData {
    private final UserRepository userRepository;
    private final UserService userService;
    private final MoralService moralService;
    private final PersonalInformationService personalInformationService;
    private final ScheduleService scheduleService;
    private final StudyResultService studyResultService;
    private final TestService testService;

    @Autowired
    public UpdateData(UserRepository userRepository, UserService userService, MoralService moralService, MoralRepository moralRepository, PersonalInformationService personalInformationService, ScheduleService scheduleService, StudyResultService studyResultService, TestService testService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.moralService = moralService;
        this.personalInformationService = personalInformationService;
        this.scheduleService = scheduleService;
        this.studyResultService = studyResultService;
        this.testService = testService;
    }

    @Async
//    @Scheduled(fixedRate = 1800000, initialDelay = 10000)
    @Scheduled(fixedRate = 600000, initialDelay = 20000)
    public void update() {
        System.out.println("update--------------------");
        log.info("update--------------------");
        userRepository.findAll().forEach(t ->
        {
                UsersDTO usersDTO = new UsersDTO();
                usersDTO.setUsername(t.getUsername());
                usersDTO.setPassword(new String(Base64.getDecoder().decode(t.getPassword())));
                if (userService.verifyUser(usersDTO).isStatus()){
                personalInformationService.savePersonalInformation(t.getUsername());
                moralService.saveMoral(t.getUsername());
                scheduleService.saveSchedule(t.getUsername());
                studyResultService.saveStudentResult(t.getUsername());
                testService.saveTest(t.getUsername());
                }



        });
    }

}
