package server.unigo.service;

import org.springframework.stereotype.Service;
import server.unigo.dto.SchedulesDTO;
import server.unigo.model.Schedules;

import java.util.List;
import java.util.Optional;


public interface ScheduleService {
    public void saveSchedule(String id);
    public List<SchedulesDTO> getSchedule(String id);

}
