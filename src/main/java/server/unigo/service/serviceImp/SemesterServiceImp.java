package server.unigo.service.serviceImp;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import server.unigo.dto.SemesterDTO;
import server.unigo.map.SemesterMapper;
import server.unigo.model.Semesters;
import server.unigo.repository.SemesterRepository;
import server.unigo.service.SemesterService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SemesterServiceImp implements SemesterService {
    private final RestTemplate restTemplate;
    private final SemesterRepository semesterRepository;

    @Autowired
    public SemesterServiceImp(RestTemplate restTemplate, SemesterRepository semesterRepository) {
        this.restTemplate = restTemplate;
        this.semesterRepository = semesterRepository;
    }


    @Override
    public void saveSemester() {
        semesterRepository.deleteAll();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<SemesterDTO>> entity = new HttpEntity<List<SemesterDTO>>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://dnunigo.herokuapp.com/dut/")
                .queryParam("command", "get_semester_list")
                .queryParam("session_id", "102170100");
        ResponseEntity<List<SemesterDTO>> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<List<SemesterDTO>>() {
                });
        List<SemesterDTO> semesterDTOList = responseEntity.getBody();
        SemesterMapper semesterMapper = Mappers.getMapper(SemesterMapper.class);
        List<Semesters> semestersList = semesterDTOList.stream()
                .map(t -> semesterMapper.mapDTOtoEntity(t)).collect(Collectors.toList());
        semestersList.forEach(t -> semesterRepository.save(t));


    }

    @Override
    public List<SemesterDTO> getSemesters(String id) {
        SemesterMapper semesterMapper = Mappers.getMapper(SemesterMapper.class);
        return semesterRepository.findAll().stream()
                .map(t -> semesterMapper.mapEntityToDTo(t)).collect(Collectors.toList());
    }
}
