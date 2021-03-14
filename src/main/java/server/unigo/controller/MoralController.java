package server.unigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.service.MoralService;

@RestController
public class MoralController {
    private final MoralService moralService;
@Autowired
    public MoralController(MoralService moralService) {
        this.moralService = moralService;
    }
    @PostMapping("saveMoral")
    public void saveMoral(){
        moralService.saveMoral();
    }
}
