package us.devfast.cheetah.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class LoginDto {
    @NotEmpty(message = "User name not empty !")
    @Schema(description = "Account login", example = "Minh An")
    private String userName;

    @Size(min = 6, max = 15)
    @Schema(description = "Account password", example = "base101")
    private String password;
}
