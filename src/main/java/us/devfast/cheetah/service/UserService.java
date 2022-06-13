package us.devfast.cheetah.service;

import us.devfast.cheetah.dto.LoginDto;
import us.devfast.cheetah.dto.RegisterDto;
import us.devfast.cheetah.dto.UserDto;
import us.devfast.cheetah.dto.UserJwtDto;

import javax.validation.constraints.Email;

public interface UserService {
    /**
     * User login
     *
     * @param input LoginDto
     * @return UserJwtDto
     */
    UserJwtDto userLogin(LoginDto input);

    /**
     * User register
     *
     * @param input RegisterDto
     * @return UserJwtDto
     * @throws Exception
     */
    UserJwtDto userRegister(RegisterDto input);

    /**
     * Check email in database
     *
     * @param email String email
     * @return boolean
     */
    boolean isCheckEmail(@Email String email);

    /**
     * Check userName in
     *
     * @param userName String
     * @return boolean
     */
    boolean isCheckUserName(String userName);

}
