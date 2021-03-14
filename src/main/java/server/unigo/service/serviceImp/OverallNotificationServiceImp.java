package server.unigo.service.serviceImp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import server.unigo.dto.NotificationsDTO;
import server.unigo.dto.OverallNotificationsDTO;
import server.unigo.model.Notifications;
import server.unigo.model.OverallNotifications;
import server.unigo.repository.NotificationRepository;
import server.unigo.repository.OverallNotificationRepository;
import server.unigo.service.NotificationService;
import server.unigo.service.OverallNotificationService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OverallNotificationServiceImp implements OverallNotificationService {
    private final OverallNotificationRepository notificationRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public OverallNotificationServiceImp(OverallNotificationRepository notificationRepository, RestTemplate restTemplate) {
        this.notificationRepository = notificationRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Void saveOverallNotification() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<OverallNotificationsDTO>> entity = new HttpEntity<List<OverallNotificationsDTO>>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://dnunigo.herokuapp.com/dut/")
                .queryParam("command", "get_class_noti")
                .queryParam("session_id", "102170100");
        ResponseEntity<List<OverallNotificationsDTO>> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<List<OverallNotificationsDTO>>() {
                });
        List<OverallNotificationsDTO> notificationsDTOList = responseEntity.getBody();
        ModelMapper modelMapper = new ModelMapper();
        List<OverallNotifications> notificationsList=notificationsDTOList.stream().map(t->modelMapper.map(t, OverallNotifications.class)).collect(Collectors.toList());
        notificationsList.forEach(t->notificationRepository.save(t));

        return null;

    }
}
