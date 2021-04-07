package server.unigo.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.dto.OverallNotificationsDTO;
import server.unigo.service.OverallNotificationService;

import java.util.List;

@RestController
@Log4j2
public class OverallNotificationController {
    private final OverallNotificationService overallNotificationService;
    private static final Logger LOGGER= LogManager.getLogger(OverallNotificationController.class);


    @Autowired
    public OverallNotificationController(OverallNotificationService overallNotificationService) {
        this.overallNotificationService = overallNotificationService;
    }

    //  Call overall notification data from https://dnunigo.herokuapp.com/dut/ Crawler server, then save data into DB
    @PostMapping("v1/overall_notifications")
    public void saveOverallNotification() {
        log.info("User with requested to v1/overall_notifications to saveOverallNotification");
        overallNotificationService.saveOverallNotification();
    }

    //  Get notification data for client
    @GetMapping("v1/overall_notifications")
    public List<OverallNotificationsDTO> getOverallNotification() {
        log.info("User with requested to v1/overall_notifications to getOverallNotification");
        return overallNotificationService.getOverallNotification();
    }
}
