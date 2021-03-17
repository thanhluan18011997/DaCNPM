package server.unigo.service;

import org.springframework.stereotype.Service;
import server.unigo.dto.TestsDTO;
import server.unigo.model.Notifications;
import server.unigo.model.Tests;

import java.util.List;

@Service
public interface TestService {
    void saveTest(String id);
    List<TestsDTO> getTest(String id);
}
