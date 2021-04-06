package server.unigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.dto.MoralsDTO;
import server.unigo.service.MoralService;

import java.util.List;

@RestController
public class MoralController {
    private final MoralService moralService;

    @Autowired
    public MoralController(MoralService moralService) {
        this.moralService = moralService;
    }

    //  Call student moral data from https://dnunigo.herokuapp.com/dut/ Crawler server, then save data into DB
    @PostMapping("v1/morals/{id}")
    public void saveMoral(@PathVariable String id) {
        moralService.saveMoral(id);
    }

    //  Get student moral data for client
    @GetMapping("v1/morals/{id}")
    public List<MoralsDTO> getMoral(@PathVariable String id) {
        return moralService.getMoral(id);
    }
}
