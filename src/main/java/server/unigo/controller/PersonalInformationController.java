package server.unigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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

    //  Call student PersonalInformation data from https://dnunigo.herokuapp.com/dut/ Crawler server, then save data into DB
    @PostMapping("v1/personal_informations/{id}")
    public void savePersonalInformation(@PathVariable String id) {
        personalInformationService.savePersonalInformation(id);
    }

    //  Get PersonalInformation data for client
    @GetMapping("v1/personal_informations/{id}")
    public PersonalInformationsDTO getPersonalInformation(@PathVariable String id) {
        PersonalInformationsDTO personalInformationsDTO=personalInformationService.getPersonalInformations(id);
        if (personalInformationsDTO==null)
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found id");
        return personalInformationsDTO;
    }
}
