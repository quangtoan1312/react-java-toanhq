package us.devfast.cheetah.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class RegisterDto extends LoginDto {
    @Email
    @NotEmpty(message = "Email not empty !")
    @Schema(description = "User email", example = "Nguyenminhan.devfast@gmail.com", required = true)
    private String email;
}
