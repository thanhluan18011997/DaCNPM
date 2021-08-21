package server.unigo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.dto.CollaboratorDTO;
import server.unigo.security.CustomUserDetail;
import server.unigo.service.CollaboratorService;

import java.util.List;

@RestController
@Log4j2
public class CollaboratorController {
    private final CollaboratorService collaboratorService;

    @Autowired
    public CollaboratorController( CollaboratorService collaboratorService) {
        this.collaboratorService = collaboratorService;
    }

    //  Get student moral data for client
    @GetMapping("v1/collaborator/{courseCode}")
    @PreAuthorize("hasAnyAuthority('READ_collaborator')")
    public List<CollaboratorDTO> getMoral(@PathVariable String courseCode, Authentication authentication) {
        log.info("User with ID="  + " requested to v1/collaborator");
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        if (customUserDetail!=null)
            return collaboratorService.getCollaborator(courseCode);
        return null;
    }
}
