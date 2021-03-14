package server.unigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.service.StudentResultService;

@RestController
public class StudentResultController {
    private final StudentResultService studentResultService;
@Autowired
    public StudentResultController(StudentResultService studentResultService) {
        this.studentResultService = studentResultService;
    }
    @PostMapping("saveStudentResult")
    public void saveStudentResult(){
        studentResultService.saveStudentResult();
    }
}
