package server.unigo.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import server.unigo.dto.StudyResultsDTO;
import server.unigo.model.StudyResults;

import java.util.List;

@Service
public interface StudyResultService {
    List<StudyResultsDTO> getStudyResult(String id);
    List<StudyResults> saveStudentResult(String id);
}
