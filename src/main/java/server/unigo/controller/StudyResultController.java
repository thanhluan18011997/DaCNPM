package server.unigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.dto.StudyResultsDTO;
import server.unigo.service.StudyResultService;

import java.util.List;

@RestController
public class StudyResultController {
    private final StudyResultService studyResultService;
@Autowired
    public StudyResultController(StudyResultService studyResultService) {
        this.studyResultService = studyResultService;
    }
    @PostMapping("saveStudyResult/{id}")
    public void saveStudentResult(@PathVariable String id){
        studyResultService.saveStudentResult(id);
    }
    @GetMapping("getStudyResults/{id}")
    public List<StudyResultsDTO> getStudyResults(@PathVariable String id){
    return studyResultService.getStudyResult(id);
    }
}
