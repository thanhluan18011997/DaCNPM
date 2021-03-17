package server.unigo.map;

import org.mapstruct.Mapper;
import server.unigo.dto.TestsDTO;
import server.unigo.model.Tests;

@Mapper
public interface TestMapper {
    Tests mapDTOtoEntity(TestsDTO testsDTO);
    TestsDTO mapEntityToDTo(Tests tests);
}
