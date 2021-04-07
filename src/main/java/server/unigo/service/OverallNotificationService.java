package server.unigo.service;

import org.springframework.stereotype.Service;
import server.unigo.dto.OverallNotificationsDTO;
import server.unigo.model.Notifications;
import server.unigo.model.OverallNotifications;

import java.util.List;


public interface OverallNotificationService {
    void saveOverallNotification();
    List<OverallNotificationsDTO> getOverallNotification();
}
