package server.unigo.service.serviceImp;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import server.unigo.Map.PersonalInformationMapper;
import server.unigo.dto.PersonalInformationsDTO;
import server.unigo.model.PersonalInformations;
import server.unigo.repository.PersonalInformationRepository;
import server.unigo.service.PersonalInformationService;

import java.util.Arrays;
import java.util.Optional;

@Service
public class PersonalInformationServiceImp implements PersonalInformationService {
    private final RestTemplate restTemplate;
//    private final Timer timer;
    private final PersonalInformationRepository personalInformationRepository;

@Autowired
    public PersonalInformationServiceImp(RestTemplate restTemplate, PersonalInformationRepository personalInformationRepository) {
        this.restTemplate = restTemplate;
//        this.timer = timer;
        this.personalInformationRepository = personalInformationRepository;
    }

    @Override
    public void savePersonalInformation() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<PersonalInformationsDTO> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://dnunigo.herokuapp.com/dut/")
                .queryParam("command", "get_personal_information")
                .queryParam("session_id", "102170100")
                .queryParam("semester_id", "2010");
        ResponseEntity<PersonalInformationsDTO> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, PersonalInformationsDTO.class);
        PersonalInformationsDTO personalInformationsDTO = responseEntity.getBody();
        PersonalInformationMapper personalInformationMapper = Mappers.getMapper(PersonalInformationMapper.class);
        PersonalInformations personalInformations = personalInformationMapper.mapDTOtoEntity(personalInformationsDTO);
//        ModelMapper modelMapper = new ModelMapper();
//        PersonalInformations personalInformations=modelMapper.map(personalInformationsDTO, PersonalInformations.class);

        Optional<PersonalInformations> personalInformations1 = personalInformationRepository.findByStudentId(personalInformations.getStudentId());
        if (personalInformations1.isPresent())
        {
            personalInformations.setId(personalInformations1.get().getId());
            personalInformationRepository.save(personalInformations);
        }

        else
            personalInformationRepository.save(personalInformations);
    }}
