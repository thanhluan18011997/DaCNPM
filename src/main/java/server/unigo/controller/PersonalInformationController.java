package server.unigo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.dto.PersonalInformationsDTO;
import server.unigo.service.PersonalInformationService;

@Log4j2
@RestController
public class PersonalInformationController {
    private final PersonalInformationService personalInformationService;

    @Autowired
    public PersonalInformationController(PersonalInformationService personalInformationService) {
        this.personalInformationService = personalInformationService;
    }

    //  Call student PersonalInformation data from https://dnunigo.herokuapp.com/dut/ Crawler server, then save data into DB
    @PostMapping("v1/personal_informations/{id}")
    public void savePersonalInformation(@PathVariable String id) {
        log.info("User with ID=" + id + " requested to v1/personal_informations/ to savePersonalInformation");
        personalInformationService.savePersonalInformation(id);
    }

    //  Get PersonalInformation data for client
    @GetMapping("v1/personal_informations/{id}")
    public PersonalInformationsDTO getPersonalInformation(@PathVariable String id) {

        log.info("User with ID=" + id + " requested to v1/personal_informations/ to getPersonalInformation");
        PersonalInformationsDTO personalInformationsDTO = personalInformationService.getPersonalInformations(id);
        return personalInformationsDTO;
    }
}
