package server.unigo.service.serviceImp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import server.unigo.dto.DetailSchedulesDTO;
import server.unigo.dto.NotificationsDTO;
import server.unigo.model.DetailSchedules;
import server.unigo.model.Notifications;
import server.unigo.repository.DetailScheduleRepository;
import server.unigo.service.DetailScheduleService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetailScheduleImp implements DetailScheduleService {
    private final DetailScheduleRepository detailScheduleRepository;
    private final RestTemplate restTemplate;
@Autowired
    public DetailScheduleImp(DetailScheduleRepository detailScheduleRepository, RestTemplate restTemplate) {
        this.detailScheduleRepository = detailScheduleRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public void saveDetailSchedule() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<DetailSchedulesDTO>> entity = new HttpEntity<List<DetailSchedulesDTO>>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://dnunigo.herokuapp.com/dut/")
                .queryParam("command", "get_class_noti")
                .queryParam("session_id", "102170100");
        ResponseEntity<List<DetailSchedulesDTO>> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<List<DetailSchedulesDTO>>() {
                });
        List<DetailSchedulesDTO> detailSchedulesDTOList = responseEntity.getBody();
        ModelMapper modelMapper = new ModelMapper();
        List<DetailSchedules> detailSchedulesList=detailSchedulesDTOList.stream().map(t -> modelMapper.map(t, DetailSchedules.class)).collect(Collectors.toList());
        detailSchedulesList.forEach(t->detailScheduleRepository.save(t));
    }
}
