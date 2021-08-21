package server.unigo.service;

import server.unigo.dto.CollaboratorDTO;

import java.util.List;

public interface CollaboratorService {
    public void saveCollaborator(Long id);

    public List<CollaboratorDTO> getCollaborator(String courseCode);

}
