package server.unigo.service.serviceImp;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import server.unigo.repository.UserRepository;
import server.unigo.security.CustomUserDetail;
public class UserDetailService implements UserDetailsService {
    @Autowired
    public UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new CustomUserDetail(userRepository.findByUsername(s).get());
    }
}
