package server.unigo.service.serviceImp;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import server.unigo.Map.MoralMapper;
import server.unigo.dto.MoralsDTO;
import server.unigo.repository.MoralRepository;
import server.unigo.service.MoralService;

import java.util.Arrays;
import java.util.List;

@Service
public class MoralServiceImp implements MoralService {
    private final MoralRepository moralRepository;
    private final RestTemplate restTemplate;
@Autowired
    public MoralServiceImp(MoralRepository moralRepository, RestTemplate restTemplate) {
        this.moralRepository = moralRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public void saveMoral() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<MoralsDTO>> entity = new HttpEntity<List<MoralsDTO>>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://dnunigo.herokuapp.com/dut/")
                .queryParam("command", "get_moral_result")
                .queryParam("session_id", "102170100")
                .queryParam("semester_id", "2010");
        ResponseEntity<List<MoralsDTO>> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<List<MoralsDTO>>() {
                });
        List<MoralsDTO> moralsDTOList = responseEntity.getBody();
//        ModelMapper modelMapper = new ModelMapper();
//        List<Morals> moralsList=moralsDTOList.stream().map(t -> modelMapper.map(t, Morals.class)).collect(Collectors.toList());
//        moralsList.forEach(t->moralRepository.save(t));
        MoralMapper moralMapper= Mappers.getMapper(MoralMapper.class);
    moralsDTOList.forEach(t-> System.out.println(moralMapper.mapDTOtoEntity(t).getRegisteredCredit()));

    }
}
