package server.unigo.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import server.unigo.model.Users;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class CustomUserDetail implements UserDetails {
    private Users users;

    public CustomUserDetail(Users users){
        this.users=users;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<String> authorities=new HashSet<>();
         users.getRoles().forEach(t->t.getPermissions().forEach(p->authorities.add(p.getPermission())) );
                 return authorities.stream().map(t->new SimpleGrantedAuthority(t)).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
