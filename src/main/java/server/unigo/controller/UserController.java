package server.unigo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.unigo.dto.BlockStatus;
import server.unigo.dto.UsersDTO;
import server.unigo.service.UserService;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PreAuthorize("hasAnyAuthority('ADMIN_Authority')")
    @PutMapping("/v1/BlockUser/{id}")
    public boolean setBlockUser(@PathVariable("id") String id, @RequestBody BlockStatus blockStatus) {
      if (userService.setBlock(id,blockStatus.getStatus())!=null)
          return true;
      return false;
    }
    @PreAuthorize("hasAnyAuthority('ADMIN_Authority')")
    @GetMapping("/v1/BlockUsers")
    public List<UsersDTO> getAllBlockUSer() {
        return userService.getAllBlockUser();
    }
}
