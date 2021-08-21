package server.unigo.map;

import org.mapstruct.Mapper;
import server.unigo.dto.FriendsDTO;
import server.unigo.dto.UsersDTO;
import server.unigo.model.Friends;
import server.unigo.model.Users;

@Mapper
public interface FriendsMapper {
    Friends mapDTOtoEntity(FriendsDTO friendsDTO);
    FriendsDTO mapEntityToDTo(Friends friends);
}
