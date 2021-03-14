package server.unigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.service.TestService;

@RestController
public class TestController {
    private final TestService testService;
@Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }
    @PostMapping("saveTest")
    public void saveTest(){
    testService.saveTest();
    }
}
