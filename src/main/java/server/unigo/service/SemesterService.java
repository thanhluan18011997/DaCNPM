package server.unigo.service;

import server.unigo.dto.SemesterDTO;

import java.util.List;

public interface SemesterService {
    public void saveSemester();
    public List<SemesterDTO> getSemesters(String id);
}
