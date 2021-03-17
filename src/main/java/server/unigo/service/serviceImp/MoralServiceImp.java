package server.unigo.service.serviceImp;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import server.unigo.map.MoralMapper;
import server.unigo.dto.MoralsDTO;
import server.unigo.model.Morals;
import server.unigo.repository.MoralRepository;
import server.unigo.repository.PersonalInformationRepository;
import server.unigo.service.MoralService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MoralServiceImp implements MoralService {
    private final MoralRepository moralRepository;
    private final RestTemplate restTemplate;
    private final PersonalInformationRepository personalInformationRepository;
@Autowired
    public MoralServiceImp(MoralRepository moralRepository, RestTemplate restTemplate, PersonalInformationRepository personalInformationRepository) {
        this.moralRepository = moralRepository;
        this.restTemplate = restTemplate;
    this.personalInformationRepository = personalInformationRepository;
}

    @Override
    public void saveMoral(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<MoralsDTO>> entity = new HttpEntity<List<MoralsDTO>>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://dnunigo.herokuapp.com/dut/")
                .queryParam("command", "get_moral_result")
                .queryParam("session_id", id);
        ResponseEntity<List<MoralsDTO>> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<List<MoralsDTO>>() {
                });
        List<MoralsDTO> moralsDTOList = responseEntity.getBody();
        MoralMapper moralMapper= Mappers.getMapper(MoralMapper.class);
        List<Morals> moralsList=moralsDTOList.stream().map(t->moralMapper.mapDTOtoEntity(t)).collect(Collectors.toList());
        moralsList.forEach(t->{
            Optional<Morals> moralsOptional=moralRepository.findBySemesterAndPersonalInformationId(id,t.getSemester());
            if (moralsOptional.isPresent())
                t.setId(moralsOptional.get().getId());
            t.setPersonalInformation(personalInformationRepository.getOne(id));
                moralRepository.save(t);

        });
    }

    @Override
    public List<MoralsDTO> getMoral(String id) {
        MoralMapper moralMapper= Mappers.getMapper(MoralMapper.class);
        return moralRepository.findAll().stream().map(t->moralMapper.mapEntityToDTo(t)).collect(Collectors.toList());
    }
}
