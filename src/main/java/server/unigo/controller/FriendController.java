package server.unigo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import server.unigo.dto.FriendsDTO;
import server.unigo.dto.MoralsDTO;
import server.unigo.security.CustomUserDetail;
import server.unigo.service.FriendService;
import server.unigo.service.MoralService;

import java.util.List;

@RestController
@Log4j2
public class FriendController {
    private final FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    //  Get student moral data for client
    @GetMapping("v1/class/{id}")
    @PreAuthorize("hasAnyAuthority('READ_class')")
    public List<FriendsDTO> getMoral(@PathVariable String id, Authentication authentication) {
        log.info("User with ID=" + id + " requested to v1/class");
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        if (customUserDetail.getUsers().getUsername().equals(id))
            return friendService.getFriends(id);
        return null;
    }
}
