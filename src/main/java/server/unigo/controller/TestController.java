package server.unigo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.dto.TestsDTO;
import server.unigo.security.CustomUserDetail;
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

    //  Get Test data for client
    @GetMapping("v1/tests/{id}")
    @PreAuthorize("hasAnyAuthority('READ_Lịch thi')")
    public List<TestsDTO> getTest(@PathVariable String id, Authentication authentication) {
        log.info("User with ID="+id+" requested to v1/tests to getTest");

        CustomUserDetail customUserDetail=(CustomUserDetail)authentication.getPrincipal();
        if (customUserDetail.getUsers().getUsername().equals(id))
            return testService.getTest(id);
        else
            return null;
    }
}
