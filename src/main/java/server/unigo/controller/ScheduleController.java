package server.unigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.dto.SchedulesDTO;
import server.unigo.service.ScheduleService;

import java.util.List;

@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;
@Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
    @PostMapping("saveSchedule/{id}")
    public void saveSchedule(@PathVariable String id){
    scheduleService.saveSchedule(id);
    }
    @GetMapping("getSchedule/{id}")
    public List<SchedulesDTO> getSchedule(@PathVariable String id){
    return scheduleService.getSchedule(id);
    }
}
