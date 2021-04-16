package server.unigo.map;

import org.mapstruct.Mapper;
import server.unigo.dto.UsersDTO;
import server.unigo.model.Users;

@Mapper
public interface UserMapper {
    Users mapDTOtoEntity(UsersDTO usersDTO);
    UsersDTO mapEntityToDTo(Users users);
}
