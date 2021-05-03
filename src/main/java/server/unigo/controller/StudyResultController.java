package server.unigo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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


    //  Get StudyResults data for client
    @GetMapping("v1/study_result/{id}")
    @PreAuthorize("hasAnyAuthority('READ_StudyResult')")
    public List<StudyResultsDTO> getStudyResults(@PathVariable("id") String id){
    log.info("User with ID="+id+" requested to v1/study_result/ to getStudyResults");
        return studyResultService.getAllStudyResult(id);
    }

    //  Get StudyResults data for client
    @GetMapping("v1/study_result/{id}/{courseName}")
    @PreAuthorize("hasAnyAuthority('READ_StudyResult')")
    public List<StudyResultsDTO> getStudyResultsByName(@PathVariable("id") String id,@PathVariable("courseName") String courseName){
        log.info("User with ID="+id+" requested to v1/study_result/ to getStudyResultsByName");
        return studyResultService.getStudyResult(id, courseName);
    }
}
