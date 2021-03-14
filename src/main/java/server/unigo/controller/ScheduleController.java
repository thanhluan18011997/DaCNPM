package server.unigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.service.ScheduleService;

@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;
@Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
    @PostMapping("saveSchedule")
    public void saveSchedule(){
    scheduleService.saveSchedule();

    }
}
