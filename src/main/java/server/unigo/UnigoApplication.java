package server.unigo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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
public class UnigoApplication implements CommandLineRunner {
//    @Autowired
//    NotificationRepository notificationRepository;
//    @Autowired
//    PersonalInformationController personalInformationController;
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
//    @Bean
//    public Timer timer(){
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 13);
//        calendar.set(Calendar.MINUTE, 9);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//
//        Date dateSchedule = calendar.getTime();
//        long period = 24 * 60 * 60 * 1000;
//
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                personalInformationController.getPersonalInformation();
//            }
//        };
//
//        Timer timer = new Timer();
//        timer.schedule(timerTask, dateSchedule, period);
//        return timer;
//    }

    public static void main(String[] args) {
        SpringApplication.run(UnigoApplication.class, args);

    }


    @Override
    public void run(String... args) throws Exception {
//        NotificationsDTO n1=new NotificationsDTO();
//        n1.setTitle("tb1");
//        n1.setContent("Nghir hoc cay");
//        ModelMapper modelMapper=new ModelMapper();
//        Notifications entity=modelMapper.map(n1,Notifications.class);
//        notificationRepository.save(entity);
    }
}


