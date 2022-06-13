package us.devfast.cheetah.service.impl;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import us.devfast.cheetah.common.config.jwt.JwtUtil;
import us.devfast.cheetah.common.config.security.service.UserDetailsServiceImpl;
import us.devfast.cheetah.common.utils.Constants;
import us.devfast.cheetah.dto.LoginDto;
import us.devfast.cheetah.dto.MapperDto;
import us.devfast.cheetah.dto.RegisterDto;
import us.devfast.cheetah.dto.UserJwtDto;
import us.devfast.cheetah.entity.UserEntity;
import us.devfast.cheetah.repository.UserRepository;
import us.devfast.cheetah.service.RoleService;
import us.devfast.cheetah.service.UserService;

import javax.validation.constraints.Email;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MapperDto mapperDto;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    /**
     * User login
     *
     * @param input LoginDto
     * @return UserJwtDto
     */
    @Override
    public UserJwtDto userLogin(LoginDto input) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(input.getUserName());

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDetails, input.getPassword(), userDetails.getAuthorities()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserEntity userEntity = this.userRepository.findUserEntityByUserName(userDetails.getUsername());

        return this.getUserJwt(authentication, userEntity);
    }

    /**
     * User register
     *
     * @param input RegisterDto
     * @return UserJwtDto
     */
    @Override
    public UserJwtDto userRegister(RegisterDto input) {
        int roleCustomerId = this.roleService.getRoleIdByName(Constants.ROLE_CUSTOMER);

        UserEntity user = this.mapperDto.convertUserEntity(input);
        user.setRole(roleCustomerId);
        user.setPassword(this.passwordEncoder.encode(input.getPassword()));
        user.setIsActive(Constants.ACTIVE);
        this.userRepository.save(user);

        return this.userLogin(input);
    }

    /**
     * Check email in system database
     *
     * @param email String
     * @return boolean
     */
    @Override
    public boolean isCheckEmail(@Email String email) {
        if (Strings.isNotEmpty(email))
            return this.userRepository.existsByEmail(email);

        return false;
    }

    /**
     * Check username in system database
     *
     * @param userName string
     * @return boolean
     */
    @Override
    public boolean isCheckUserName(String userName) {
        if (Strings.isNotEmpty(userName))
            return this.userRepository.existsByUserName(userName);

        return false;
    }

    /**
     * get UserJwtDto by Authentication and UserEntity
     *
     * @param authentication Authentication
     * @param userEntity UserEntity
     * @return UserJwtDto
     */
    private UserJwtDto getUserJwt(Authentication authentication, UserEntity userEntity)
    {
        String jwtToken = jwtUtil.generateJwtToken(authentication);
        String role = userEntity.getRoleUser().getName();

        return new UserJwtDto(jwtToken, role, this.mapperDto.convertUserDto(userEntity));
    }

}
