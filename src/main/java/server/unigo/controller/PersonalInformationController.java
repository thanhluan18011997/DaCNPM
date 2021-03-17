package server.unigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.unigo.dto.PersonalInformationsDTO;
import server.unigo.dto.UserDTO;
import server.unigo.model.PersonalInformations;
import server.unigo.service.PersonalInformationService;

@RestController
public class PersonalInformationController {
    private final PersonalInformationService personalInformationService;

    @Autowired
    public PersonalInformationController(PersonalInformationService personalInformationService) {
        this.personalInformationService = personalInformationService;
    }

    @PostMapping("savePersonalInformation/{id}")
    public void savePersonalInformation(@PathVariable String id) {
        personalInformationService.savePersonalInformation(id);
    }
    @GetMapping("getPersonalInformation/{id}")
    public PersonalInformationsDTO getPersonalInformation(@PathVariable String id){
        return personalInformationService.getPersonalInformations(id);
    }
}
