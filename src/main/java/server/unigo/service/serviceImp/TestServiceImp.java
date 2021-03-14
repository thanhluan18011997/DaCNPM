package server.unigo.service.serviceImp;

import org.aspectj.weaver.ast.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import server.unigo.dto.OverallNotificationsDTO;
import server.unigo.dto.TestsDTO;
import server.unigo.model.OverallNotifications;
import server.unigo.model.Tests;
import server.unigo.repository.TestRepository;
import server.unigo.service.TestService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestServiceImp implements TestService {
    private final TestRepository testRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public TestServiceImp(TestRepository testRepository, RestTemplate restTemplate) {
        this.testRepository = testRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public void saveTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<TestsDTO>> entity = new HttpEntity<List<TestsDTO>>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://dnunigo.herokuapp.com/dut/")
                .queryParam("command", "get_tests")
                .queryParam("session_id", "102170100")
                .queryParam("semester_id", "2010");
        ResponseEntity<List<TestsDTO>> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<List<TestsDTO>>() {
                });
        List<TestsDTO> testsDTOList = responseEntity.getBody();
        ModelMapper modelMapper = new ModelMapper();
        List<Tests> testsList = testsDTOList.stream().map(t -> modelMapper.map(t, Tests.class)).collect(Collectors.toList());
        testsList.forEach(t -> testRepository.save(t));
    }
}
