package us.devfast.cheetah.controller.api;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.devfast.cheetah.common.config.jwt.JwtUtil;
import us.devfast.cheetah.common.config.security.service.UserDetailsServiceImpl;
import us.devfast.cheetah.common.utils.Constants;
import us.devfast.cheetah.controller.BaseController;
import us.devfast.cheetah.dto.UserDto;
import us.devfast.cheetah.dto.UserJwtDto;
import us.devfast.cheetah.dto.LoginDto;
import us.devfast.cheetah.dto.RegisterDto;
import us.devfast.cheetah.entity.UserEntity;
import us.devfast.cheetah.repository.RoleRepository;
import us.devfast.cheetah.repository.UserRepository;
import us.devfast.cheetah.service.impl.UserServiceImpl;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController {
    /**
     * Message error authController
     */
    private final static String MESSAGE_ERROR_LOG = "AuthController error !";

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto input, Errors errors) {
        try{
            if (errors.hasErrors())
                return this.error(this.errorResponse(errors));

            if (this.userService.isCheckUserName(input.getUserName())) {
                return this.error(this.errorResponse("Username is already taken !"));
            }
            if (this.userService.isCheckEmail(input.getEmail())) {
                return this.error(this.errorResponse("Email is already in use !"));
            }

            // Create new user's account
            UserJwtDto output = this.userService.userRegister(input);

            return this.success(this.successResponse(Constants.REGISTER_SUCCESS, output));
        }catch (Exception ex){
            log.error(MESSAGE_ERROR_LOG, ex);
            return this.serverError();
        }
    }

    /**
     * User login
     *
     * @param input LoginDto
     * @return user info data
     */
    @PostMapping("/login")
    @Parameter(name = "Login api")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto input, Errors errors)
    {
        try{
            if (errors.hasErrors())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.errorResponse(errors));

            UserJwtDto output = this.userService.userLogin(input);
            return ResponseEntity.ok(this.successResponse(Constants.LOGIN_SUCCESS, output));

        }catch (AuthenticationException ex)
        {
            return this.error(this.errorResponse(Constants.LOGIN_ERROR));
        }catch (Exception ex){
            log.error(MESSAGE_ERROR_LOG, ex);
            return this.serverError();
        }
    }
}
