package server.unigo.service;

import org.springframework.stereotype.Service;
import server.unigo.dto.NotificationsDTO;
import server.unigo.dto.OverallNotificationsDTO;
import server.unigo.model.Notifications;

import java.util.List;

@Service
public interface NotificationService {
    void saveNotification();
    List<NotificationsDTO> getNotification();
}
