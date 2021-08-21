package server.unigo.service.serviceImp;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import server.unigo.dto.CollaboratorDTO;
import server.unigo.dto.FriendsDTO;
import server.unigo.map.CollaboratorsMapper;
import server.unigo.map.FriendsMapper;
import server.unigo.model.Collaborators;
import server.unigo.model.PersonalInformations;
import server.unigo.model.Schedules;
import server.unigo.repository.CollaboratorRepository;
import server.unigo.repository.PersonalInformationRepository;
import server.unigo.repository.ScheduleRepository;
import server.unigo.service.CollaboratorService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CollaboratorServiceImp implements CollaboratorService {
    private final RestTemplate restTemplate;
    private final CollaboratorRepository collaboratorRepository;
    private final PersonalInformationRepository personalInformationRepository;
    private final ScheduleRepository scheduleRepository;



    @Autowired
    public CollaboratorServiceImp(RestTemplate restTemplate, CollaboratorRepository collaboratorRepository, PersonalInformationRepository personalInformationRepository, ScheduleRepository scheduleRepository) {
        this.restTemplate = restTemplate;
        this.collaboratorRepository = collaboratorRepository;
        this.personalInformationRepository = personalInformationRepository;
        this.scheduleRepository = scheduleRepository;
    }



//    public List<FriendsDTO> getFriends(String id) {
//        Optional<PersonalInformations> personalInformations = personalInformationRepository.findByStudentId(id);
//        if (!personalInformations.isPresent())
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found id");
//        FriendsMapper friendsMapper = Mappers.getMapper(FriendsMapper.class);
//        return friendRepository.findByPersonalInformationID(id).get().stream().map(t ->
//                friendsMapper.mapEntityToDTo(t)
//        ).collect(Collectors.toList());
//
//    }

    @Override
    public void saveCollaborator(Long scheduleId) {

        Optional<Schedules> schedulesOptional=scheduleRepository.findById(scheduleId);
        if (!schedulesOptional.isPresent())
            return;
        String classCode="";
        String courseCode=schedulesOptional.get().getCourseCode();
        String [] courseArr=courseCode.split("\\.");
        for (String str:courseArr) {
            classCode+=str.trim();
        }
        String id=schedulesOptional.get().getPersonalInformation().getStudentId();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<CollaboratorDTO>> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://dnunigo.herokuapp.com/dut/")
                .queryParam("command", "get_colab_info")
                .queryParam("class_code",classCode )
                .queryParam("session_id", id);
        ResponseEntity<List<CollaboratorDTO>> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<List<CollaboratorDTO>>() {
                });
        List<CollaboratorDTO> collaboratorDTOList = responseEntity.getBody();
        CollaboratorsMapper collaboratorsMapper = Mappers.getMapper(CollaboratorsMapper.class);
        List<Collaborators> collaboratorsList = collaboratorDTOList.stream().map(t->collaboratorsMapper.mapDTOtoEntity(t)).collect(Collectors.toList());
        Set<Collaborators> collaboratorsList1=new HashSet<>();
        collaboratorsList.forEach(  t -> {
            Optional<Collaborators> collaboratorsOptional = collaboratorRepository.findBySutdent_id( t.getSutdent_id());
            if (collaboratorsOptional.isPresent())
                t.setId(collaboratorsOptional.get().getId());
            Collaborators collaborators=collaboratorRepository.save(t);
            collaboratorsList1.add(collaborators);
        });
        schedulesOptional.get().setCollaborators(collaboratorsList1);
        scheduleRepository.save(schedulesOptional.get());

    }

    @Override
    public List<CollaboratorDTO> getCollaborator(String courseCode) {
        CollaboratorsMapper collaboratorsMapper=Mappers.getMapper(CollaboratorsMapper.class);
        Optional<List<Collaborators>> collaboratorsList=scheduleRepository.findCollaboratorByCoursecode(courseCode);
        if (collaboratorsList.isPresent())
        {
            return  collaboratorsList.get().stream().map(t->collaboratorsMapper.mapEntityToDTo(t)).collect(Collectors.toList());
        }
        return null;
    }


//    @Override
//    public List<MoralsDTO> getMoral(String id) {
//
//    }
}
