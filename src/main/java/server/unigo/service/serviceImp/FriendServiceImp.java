package server.unigo.service.serviceImp;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import server.unigo.dto.FriendsDTO;
import server.unigo.map.FriendsMapper;
import server.unigo.map.TestMapper;
import server.unigo.model.Friends;
import server.unigo.model.PersonalInformations;
import server.unigo.model.Tests;
import server.unigo.repository.FriendRepository;
import server.unigo.repository.PersonalInformationRepository;
import server.unigo.service.FriendService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendServiceImp implements FriendService {
    private final RestTemplate restTemplate;
    private final FriendRepository friendRepository;
    private final PersonalInformationRepository personalInformationRepository;


    @Autowired
    public FriendServiceImp(RestTemplate restTemplate, FriendRepository friendRepository, PersonalInformationRepository personalInformationRepository) {
        this.restTemplate = restTemplate;
        this.friendRepository = friendRepository;
        this.personalInformationRepository = personalInformationRepository;
    }


    @Override
    public void saveFriend(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<FriendsDTO>> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://dnunigo.herokuapp.com/dut/")
                .queryParam("command", "get_class_friends")
                .queryParam("session_id", id);
        ResponseEntity<List<FriendsDTO>> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<List<FriendsDTO>>() {
                });
        List<FriendsDTO> friendsDTOList = responseEntity.getBody();
        FriendsMapper friendsMapper = Mappers.getMapper(FriendsMapper.class);
        List<Friends> friendsList = friendsDTOList.stream().map(t->friendsMapper.mapDTOtoEntity(t)).collect(Collectors.toList());
        friendsList.forEach(  t -> {
            Optional<Friends> friendsOptional = friendRepository.findByPersonalInformationIDAndFriendID(id, t.getSutdent_id());
            if (friendsOptional.isPresent())
                t.setId(friendsOptional.get().getId());
            t.setPersonalInformation(personalInformationRepository.getOne(id));
            friendRepository.save(t);
        });

    }

    @Override
    public List<FriendsDTO> getFriends(String id) {
        Optional<PersonalInformations> personalInformations = personalInformationRepository.findByStudentId(id);
        if (!personalInformations.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found id");
        FriendsMapper friendsMapper = Mappers.getMapper(FriendsMapper.class);
        return friendRepository.findByPersonalInformationID(id).get().stream().map(t ->
                friendsMapper.mapEntityToDTo(t)
        ).collect(Collectors.toList());

    }


//    @Override
//    public List<MoralsDTO> getMoral(String id) {
//
//    }
}
