package server.unigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.dto.NotificationsDTO;
import server.unigo.dto.OverallNotificationsDTO;
import server.unigo.service.NotificationService;

import java.util.List;

@RestController
public class NotificationController {
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    //  Call student notification data from https://dnunigo.herokuapp.com/dut/ Crawler server, then save data into DB
    @PostMapping("saveNotification")
    public void saveNotification() {
        notificationService.saveNotification();
    }

    //  Get notification data for client
    @GetMapping("getNotification")
    public List<NotificationsDTO> getNotification() {
        return notificationService.getNotification();
    }
}
