package server.unigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.dto.TestsDTO;
import server.unigo.model.Tests;
import server.unigo.service.TestService;

import java.util.List;

@RestController
public class TestController {
    private final TestService testService;
@Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }
    @PostMapping("saveTest/{id}")
    public void saveTest(@PathVariable String id){
    testService.saveTest(id);
    }
    @GetMapping("getTest/{id}")
    public List<TestsDTO> getTest(@PathVariable String id){
    return testService.getTest(id);
    }
}
