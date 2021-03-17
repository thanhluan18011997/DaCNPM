package server.unigo.map;

import org.mapstruct.Mapper;
import server.unigo.dto.MoralsDTO;
import server.unigo.model.Morals;

@Mapper
public interface MoralMapper {
    Morals mapDTOtoEntity(MoralsDTO   moralsDTO);
    MoralsDTO mapEntityToDTo(Morals morals);
}
