package server.unigo.service;

import server.unigo.dto.FriendsDTO;

import java.util.List;

public interface FriendService {
    public void saveFriend(String id);
    public List<FriendsDTO> getFriends(String id);

}
