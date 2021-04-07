package server.unigo.service;

import org.springframework.stereotype.Service;
import server.unigo.dto.PersonalInformationsDTO;
import server.unigo.model.PersonalInformations;


public interface PersonalInformationService {
    void savePersonalInformation(String id);
    PersonalInformationsDTO getPersonalInformations(String id);
}
