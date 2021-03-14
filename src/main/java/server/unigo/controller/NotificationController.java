package server.unigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.service.NotificationService;

@RestController
public class NotificationController {
    private final NotificationService notificationService;
@Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
@PostMapping("saveNotification")
    public void saveNotification(){
        notificationService.saveNotification();
    }
}
