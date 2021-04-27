package server.unigo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.dto.SchedulesDTO;
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
    @PreAuthorize("hasAnyAuthority('READ_Schedule')")
    public List<SchedulesDTO> getSchedule(@PathVariable String id) {
        log.info("User with ID="+id+" requested to v1/schedules/ to getSchedule");
        return scheduleService.getSchedule(id);
    }
}
