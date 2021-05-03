package server.unigo.service;

import server.unigo.dto.StudyResultsDTO;
import server.unigo.model.StudyResults;

import java.util.List;


public interface StudyResultService {
    List<StudyResultsDTO> getAllStudyResult(String id);
    List<StudyResults> saveStudentResult(String id);
    List<StudyResultsDTO> getStudyResult(String id, String name);
}
