package us.devfast.cheetah.dto;

import lombok.Data;

@Data
public class UserJwtDto extends UserDto {
    private String token;
    private String role;

    public UserJwtDto() {
    }

    public UserJwtDto(String token, String role, UserDto userInfo) {
        this.token = token;
        this.role = role;
        this.setUserName(userInfo.getUserName());
        this.setFullName(userInfo.getFullName());
        this.setEmail(userInfo.getEmail());
    }
}
