package server.unigo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.dto.StudyResultsDTO;
import server.unigo.service.StudyResultService;

import java.util.List;

@RestController
@Log4j2
public class StudyResultController {
    private final StudyResultService studyResultService;
@Autowired
    public StudyResultController(StudyResultService studyResultService) {
        this.studyResultService = studyResultService;
    }
    //  Call student StudyResult data from https://dnunigo.herokuapp.com/dut/ Crawler server, then save data into DB
    @PostMapping("v1/study_result/{id}")
    public void saveStudentResult(@PathVariable String id){

        log.info("User with ID="+id+" requested to v1/study_result/ to saveStudentResult");
        studyResultService.saveStudentResult(id);
    }

    //  Get StudyResults data for client
    @GetMapping("v1/study_result/{id}")
    public List<StudyResultsDTO> getStudyResults(@PathVariable String id){
    log.info("User with ID="+id+" requested to v1/study_result/ to getStudyResults");

        return studyResultService.getStudyResult(id);
    }
}
