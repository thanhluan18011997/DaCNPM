package server.unigo.service.serviceImp;

import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import server.unigo.dto.NotificationsDTO;
import server.unigo.dto.OverallNotificationsDTO;
import server.unigo.map.NotificationMapper;
import server.unigo.map.OverallNotificationMapper;
import server.unigo.model.Notifications;
import server.unigo.repository.NotificationRepository;
import server.unigo.service.NotificationService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImp implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public NotificationServiceImp(NotificationRepository notificationRepository, RestTemplate restTemplate) {
        this.notificationRepository = notificationRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public void saveNotification() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<NotificationsDTO>> entity = new HttpEntity<List<NotificationsDTO>>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://dnunigo.herokuapp.com/dut/")
                .queryParam("command", "get_class_noti")
                .queryParam("session_id", "102170100");
        ResponseEntity<List<NotificationsDTO>> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<List<NotificationsDTO>>() {
                });
        List<NotificationsDTO> notificationsDTOList = responseEntity.getBody();
        ModelMapper modelMapper = new ModelMapper();
        List<Notifications> notificationsList=notificationsDTOList.stream().map(t -> modelMapper.map(t, Notifications.class)).collect(Collectors.toList());
        notificationsList.forEach(t->notificationRepository.save(t));
    }
    public List<NotificationsDTO> getNotification() {
        NotificationMapper notificationMapper= Mappers.getMapper(NotificationMapper.class);
        List<NotificationsDTO> notificationsDTOList= notificationRepository.findAll().stream().map(t->
                notificationMapper.mapEntityToDTo(t)
        ).collect(Collectors.toList());
        return notificationsDTOList;
    }
}
