package server.unigo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.dto.TestsDTO;
import server.unigo.service.TestService;

import java.util.List;

@RestController
@Log4j2
public class TestController {
    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    //  Call student test data from https://dnunigo.herokuapp.com/dut/ Crawler server, then save data into DB
    @PostMapping("v1/tests/{id}")
    public void saveTest(@PathVariable String id) {
        log.info("User with ID="+id+" requested to v1/tests to saveTest");
        testService.saveTest(id);
    }

    //  Get Test data for client
    @GetMapping("v1/tests/{id}")
    public List<TestsDTO> getTest(@PathVariable String id) {
        log.info("User with ID="+id+" requested to v1/tests to getTest");
        return testService.getTest(id);
    }
}
