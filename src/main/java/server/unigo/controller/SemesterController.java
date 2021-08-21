package server.unigo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.dto.SemesterDTO;
import server.unigo.security.CustomUserDetail;
import server.unigo.service.SemesterService;

import java.util.List;

@RestController
@Log4j2
public class SemesterController {
    private final SemesterService semesterService;

    @Autowired
    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    //  Call student notification data from https://dnunigo.herokuapp.com/dut/ Crawler server, then save data into DB
    @Async
    @Scheduled(fixedRate = 90000000, initialDelay = 20000)
    public void saveSemester() {
        log.info("User with requested to v1/semesters to saveSemester");
        semesterService.saveSemester();
    }

    //  Get notification data for client
    @GetMapping("/v1/semesters/{id}")
    @PreAuthorize("hasAnyAuthority('READ_semester')")
    public List<SemesterDTO> getNotification(@PathVariable String id, Authentication authentication) {

        log.info("User with ID="+id+" requested to v1/semesters/ to getMoral");
        CustomUserDetail customUserDetail=(CustomUserDetail)authentication.getPrincipal();
        if (customUserDetail.getUsers().getUsername().equals(id))
            return semesterService.getSemesters(id);
        else
            return null;
    }
}
