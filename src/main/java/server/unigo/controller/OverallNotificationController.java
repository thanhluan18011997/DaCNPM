package server.unigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.dto.OverallNotificationsDTO;
import server.unigo.service.NotificationService;
import server.unigo.service.OverallNotificationService;

import java.util.List;

@RestController
public class OverallNotificationController {
    private final OverallNotificationService overallNotificationService;
@Autowired
    public OverallNotificationController(OverallNotificationService overallNotificationService) {
        this.overallNotificationService = overallNotificationService;
    }
@PostMapping("saveOverallNotification")
    public void saveOverallNotification(){
    overallNotificationService.saveOverallNotification();
    }
    @GetMapping("getOverallNotification")
    public List<OverallNotificationsDTO> getOverallNotification(){
    return overallNotificationService.getOverallNotification();
    }
}
