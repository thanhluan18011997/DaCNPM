package server.unigo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;
import server.unigo.controller.PersonalInformationController;
import server.unigo.dto.NotificationsDTO;
import server.unigo.model.Notifications;
import server.unigo.repository.NotificationRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class UnigoApplication  {
    public static void main(String[] args) {
        SpringApplication.run(UnigoApplication.class, args);

    }

}


