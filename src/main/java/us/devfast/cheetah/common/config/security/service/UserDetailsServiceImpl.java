package us.devfast.cheetah.common.config.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import us.devfast.cheetah.entity.UserEntity;
import us.devfast.cheetah.repository.RoleRepository;
import us.devfast.cheetah.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userRepository.findUserEntityByUserName(username);
        if (user == null)
            throw new UsernameNotFoundException("User Not Found : " + username);

        if (user.getRoleUser() == null)
            user.setRoleUser(this.roleRepository.findRoleEntityById(user.getRole()));

        return UserDetailImpl.build(user);
    }
}
