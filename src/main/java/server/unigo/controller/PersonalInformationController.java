package server.unigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.service.PersonalInformationService;

@RestController
public class PersonalInformationController {
    private final PersonalInformationService personalInformationService;

    @Autowired
    public PersonalInformationController(PersonalInformationService personalInformationService) {
        this.personalInformationService = personalInformationService;
    }

    @PostMapping("savePersonalInformation")
    public void savePersonalInformation() {
        personalInformationService.savePersonalInformation();
    }
}
