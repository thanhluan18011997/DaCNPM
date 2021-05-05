package server.unigo.service.serviceImp;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import server.unigo.dto.StudyResultsDTO;
import server.unigo.map.StudyResultMapper;
import server.unigo.model.PersonalInformations;
import server.unigo.model.StudyResults;
import server.unigo.repository.PersonalInformationRepository;
import server.unigo.repository.StudyResultRepository;
import server.unigo.service.StudyResultService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudyResultServiceImp implements StudyResultService {
    private final StudyResultRepository studyResultRepository;
    private final PersonalInformationRepository personalInformationRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public StudyResultServiceImp(StudyResultRepository studyResultRepository, PersonalInformationRepository personalInformationRepository, RestTemplate restTemplate) {
        this.studyResultRepository = studyResultRepository;
        this.personalInformationRepository = personalInformationRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<StudyResultsDTO> getAllStudyResult(String id) {
        Optional<PersonalInformations> personalInformations = personalInformationRepository.findByStudentId(id);
        if (!personalInformations.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found id");
        StudyResultMapper studyResultMapper = Mappers.getMapper(StudyResultMapper.class);
        return studyResultRepository.findByPersonalInformationID(id).get().stream().map(t -> studyResultMapper.mapEntityToDTo(t)).collect(Collectors.toList());

    }

    //when using generic, appear cast error :" return hash map instead of json "
    @Override
    public List<StudyResults> saveStudentResult(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<StudyResultsDTO>> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://dnunigo.herokuapp.com/dut/")
                .queryParam("command", "get_study_result")
                .queryParam("session_id", id)
                .queryParam("semester_id", "2020");
        ResponseEntity<List<StudyResultsDTO>> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<List<StudyResultsDTO>>() {
                });
        List<StudyResultsDTO> studyResultsDTOList = responseEntity.getBody();
        StudyResultMapper studyResultMapper = Mappers.getMapper(StudyResultMapper.class);
        List<StudyResults> studyResultsList = studyResultsDTOList.stream()
                .map(t -> studyResultMapper.mapDTOtoEntity(t)).collect(Collectors.toList());

        studyResultsList.forEach(t -> {
            Optional<StudyResults> studyResultsOptional = studyResultRepository.findByPersonalInformationIdAndCourseCode(id, t.getCourseCode());
            if (studyResultsOptional.isPresent())
                t.setId(studyResultsOptional.get().getId());
            t.setPersonalInformation(personalInformationRepository.getOne(id));
            studyResultRepository.save(t);
        });
        return studyResultsList;
    }

    @Override
    public List<StudyResultsDTO> getStudyResult(String id, String name) {
        Optional<List<StudyResults>> studyResultsOptional = studyResultRepository.findByIdAndCourseNameContaining(id,name);
        StudyResultMapper studyResultMapper=Mappers.getMapper(StudyResultMapper.class);
        if (studyResultsOptional.isPresent())
            return studyResultsOptional.get().stream().map(t->studyResultMapper.mapEntityToDTo(t)).collect(Collectors.toList());
        return null;
    }
}
