package server.unigo.service.serviceImp;

import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import server.unigo.Map.ScheduleMapper;
import server.unigo.dto.DetailSchedulesDTO;
import server.unigo.dto.SchedulesDTO;
import server.unigo.model.DetailSchedules;
import server.unigo.model.Schedules;
import server.unigo.model.WeeklySchedules;
import server.unigo.repository.DetailScheduleRepository;
import server.unigo.repository.ScheduleRepository;
import server.unigo.repository.WeeklySchedulesRepository;
import server.unigo.service.ScheduleService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ScheduleServiceImp implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final DetailScheduleRepository detailScheduleRepository;
    private final RestTemplate restTemplate;
    private final WeeklySchedulesRepository weeklySchedulesRepository;

    @Autowired
    public ScheduleServiceImp(ScheduleRepository scheduleRepository, DetailScheduleRepository detailScheduleRepository, RestTemplate restTemplate, WeeklySchedulesRepository weeklySchedulesRepository) {
        this.scheduleRepository = scheduleRepository;
        this.detailScheduleRepository = detailScheduleRepository;
        this.restTemplate = restTemplate;
        this.weeklySchedulesRepository = weeklySchedulesRepository;
    }

    @Override
    public void saveSchedule() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<SchedulesDTO>> entity = new HttpEntity<List<SchedulesDTO>>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://dnunigo.herokuapp.com/dut/")
                .queryParam("command", "get_schedule")
                .queryParam("session_id", "102170100")
                .queryParam("semester_id", "2010");
        ResponseEntity<List<SchedulesDTO>> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<List<SchedulesDTO>>() {
                });
        List<SchedulesDTO> schedulesDTOList = responseEntity.getBody();
        ModelMapper modelMapper = new ModelMapper();

        for (SchedulesDTO schedulesDTO : schedulesDTOList) {
            Set<DetailSchedules> detailSchedulesSet = new HashSet<>();
            for (DetailSchedulesDTO detailSchedulesDTO : schedulesDTO.getWeeklySchedule().getDetailSchedules()) {
                DetailSchedules detailSchedules = modelMapper.map(detailSchedulesDTO, DetailSchedules.class);
//                DetailSchedules detailSchedules1 = detailScheduleRepository.save(detailSchedules);
                detailSchedulesSet.add(detailSchedules);
            }
            WeeklySchedules weeklySchedules = modelMapper.map(schedulesDTO.getWeeklySchedule(), WeeklySchedules.class);
            weeklySchedules.setDetailSchedules(detailSchedulesSet);
            ScheduleMapper scheduleMapper = Mappers.getMapper(ScheduleMapper.class);
            server.unigo.model.Schedules schedules = scheduleMapper.mapDTOtoEntity(schedulesDTO);
            Schedules schedules1 = scheduleRepository.save(schedules);
            weeklySchedules.setSchedules(schedules1);
            WeeklySchedules weeklySchedules1 = weeklySchedulesRepository.save(weeklySchedules);
            weeklySchedules1.getDetailSchedules().forEach(t->{
                t.setWeeklySchedule(weeklySchedules1);
                detailScheduleRepository.save(t);
            });
            schedules1.setWeeklySchedules(weeklySchedules1);


        }


    }
}
