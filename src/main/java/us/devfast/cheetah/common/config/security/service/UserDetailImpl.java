package us.devfast.cheetah.common.config.security.service;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import us.devfast.cheetah.entity.UserEntity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDetailImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private int id;

    private String userName;
    @JsonIgnore
    private String password;

    private String email;

    private String fullName;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailImpl(int id, String userName, String password, String email, String fullName, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.authorities = authorities;
    }

    public static UserDetailImpl build(UserEntity user) {

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRoleUser().getName()));

        return new UserDetailImpl(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getFullName(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
