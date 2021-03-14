package server.unigo.service;

import org.springframework.stereotype.Service;
import server.unigo.model.Notifications;

import java.util.List;

@Service
public interface NotificationService {
    void saveNotification();
}
