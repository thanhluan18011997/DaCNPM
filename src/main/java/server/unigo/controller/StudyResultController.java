package server.unigo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.dto.StudyResultsDTO;
import server.unigo.security.CustomUserDetail;
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
    @PreAuthorize("hasAnyAuthority('READ_Điểm')")
    public List<StudyResultsDTO> getStudyResults(@PathVariable("id") String id,Authentication authentication){
    log.info("User with ID="+id+" requested to v1/study_result/ to getStudyResults");
        CustomUserDetail customUserDetail=(CustomUserDetail)authentication.getPrincipal();
        if (customUserDetail.getUsers().getUsername().equals(id))
            return studyResultService.getAllStudyResult(id);
        else
            return null;
    }

    //  Get StudyResults data for client
    @GetMapping("v1/study_result/{id}/{courseName}")
    @PreAuthorize("hasAnyAuthority('READ_Điểm')")
    public List<StudyResultsDTO> getStudyResultsByName(@PathVariable("id") String id, @PathVariable("courseName") String courseName, Authentication authentication){
        log.info("User with ID="+id+" requested to v1/study_result/ to getStudyResultsByName");
        CustomUserDetail customUserDetail=(CustomUserDetail)authentication.getPrincipal();
        if (customUserDetail.getUsers().getUsername().equals(id))
            return studyResultService.getStudyResult(id, courseName);
        else
            return null;

    }
}
