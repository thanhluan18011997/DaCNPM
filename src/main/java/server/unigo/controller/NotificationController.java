package server.unigo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.dto.NotificationsDTO;
import server.unigo.service.NotificationService;

import java.util.List;

@RestController
@Log4j2
public class NotificationController {
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    //  Call student notification data from https://dnunigo.herokuapp.com/dut/ Crawler server, then save data into DB
    @Async
    @Scheduled(fixedRate = 300000, initialDelay = 10000)
    @PostMapping("/v1/notifications")
    public void saveNotification() {
        log.info("User with requested to v1/notifications to saveNotification");
        notificationService.saveNotification();
    }

    //  Get notification data for client
    @GetMapping("/v1/notifications")
    public List<NotificationsDTO> getNotification() {

        log.info("User with requested to v1/notifications to getNotification");
        return notificationService.getNotification();
    }
}
