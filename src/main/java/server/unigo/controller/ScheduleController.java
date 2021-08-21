package server.unigo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import server.unigo.dto.SchedulesDTO;
import server.unigo.security.CustomUserDetail;
import server.unigo.service.ScheduleService;

import java.util.List;

@RestController
@Log4j2
public class ScheduleController {
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    //  Get Schedule data for client
    @GetMapping("v1/schedules/{id}")
    @PreAuthorize("hasAnyAuthority('READ_Lịch học')")
    public List<SchedulesDTO> getSchedule(@PathVariable("id") String id, @RequestParam(name = "semesterId",required = false) String semesterId,@RequestParam(name = "courseName",required = false) String courseName ,Authentication authentication) {
        log.info("User with ID="+id+" requested to v1/schedules/ to getSchedule");
        CustomUserDetail customUserDetail=(CustomUserDetail)authentication.getPrincipal();
        if (customUserDetail.getUsers().getUsername().equals(id))
            return scheduleService.getSchedule(id,semesterId,courseName);
        else
            return null;
    }

}
