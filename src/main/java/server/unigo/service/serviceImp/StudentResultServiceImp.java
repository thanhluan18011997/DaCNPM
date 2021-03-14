package server.unigo.service.serviceImp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import server.unigo.dto.StudyResultsDTO;
import server.unigo.dto.TestsDTO;
import server.unigo.model.StudyResults;
import server.unigo.model.Tests;
import server.unigo.repository.StudentResultRepository;
import server.unigo.service.StudentResultService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentResultServiceImp implements StudentResultService {
    private final StudentResultRepository studentResultRepository;
    private final RestTemplate restTemplate;
@Autowired
    public StudentResultServiceImp(StudentResultRepository studentResultRepository, RestTemplate restTemplate) {
        this.studentResultRepository = studentResultRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public void saveStudentResult() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<StudyResultsDTO>> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://dnunigo.herokuapp.com/dut/")
                .queryParam("command", "get_study_result")
                .queryParam("session_id", "102170100")
                .queryParam("semester_id", "2010");
        ResponseEntity<List<StudyResultsDTO>> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<List<StudyResultsDTO>>() {
                });
        List<StudyResultsDTO> studyResultsDTOList = responseEntity.getBody();
        ModelMapper modelMapper = new ModelMapper();
        List<StudyResults> studyResultsList = studyResultsDTOList.stream().map(t -> modelMapper.map(t, StudyResults.class)).collect(Collectors.toList());
        studyResultsList.forEach(t -> studentResultRepository.save(t));
    }
}
