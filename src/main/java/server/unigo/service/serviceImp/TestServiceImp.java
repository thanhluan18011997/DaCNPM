package server.unigo.service.serviceImp;

import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import server.unigo.dto.TestsDTO;
import server.unigo.map.TestMapper;
import server.unigo.model.PersonalInformations;
import server.unigo.model.Tests;
import server.unigo.repository.PersonalInformationRepository;
import server.unigo.repository.TestRepository;
import server.unigo.service.TestService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestServiceImp implements TestService {
    private final TestRepository testRepository;
    private final RestTemplate restTemplate;
    private final PersonalInformationRepository personalInformationRepository;

    @Autowired
    public TestServiceImp(TestRepository testRepository, RestTemplate restTemplate, PersonalInformationRepository personalInformationRepository) {
        this.testRepository = testRepository;
        this.restTemplate = restTemplate;
        this.personalInformationRepository = personalInformationRepository;
    }

    @Override
    public void saveTest(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<TestsDTO>> entity = new HttpEntity<List<TestsDTO>>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://dnunigo.herokuapp.com/dut/")
                .queryParam("command", "get_tests")
                .queryParam("session_id", id)
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
        testsList.forEach(
                t -> {
                    Optional<Tests> testsOptional = testRepository.findByPersonalInformationIdAndCourseCode(id, t.getCourseCode());
                    if (testsOptional.isPresent())
                        t.setId(testsOptional.get().getId());
                    t.setPersonalInformation(personalInformationRepository.getOne(id));
                    testRepository.save(t);
                }
        );

    }

    @Override
    public List<TestsDTO> getTest(String id) {
        Optional<PersonalInformations> personalInformations = personalInformationRepository.findByStudentId(id);
        if (!personalInformations.isPresent())
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found id");
        TestMapper testMapper = Mappers.getMapper(TestMapper.class);
        return testRepository.findByPersonalInformationID(id).get().stream().map(t ->
                testMapper.mapEntityToDTo(t)
        ).collect(Collectors.toList());

    }
}
