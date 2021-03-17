package server.unigo.service;

import org.springframework.stereotype.Service;
import server.unigo.dto.MoralsDTO;

import java.util.List;

@Service
public interface MoralService {
    void saveMoral(String id);
    List<MoralsDTO> getMoral(String id);
}
