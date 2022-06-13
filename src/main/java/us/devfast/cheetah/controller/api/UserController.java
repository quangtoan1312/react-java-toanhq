package us.devfast.cheetah.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.devfast.cheetah.controller.BaseController;
import us.devfast.cheetah.service.UserService;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * Message error log in file
     */
    private final static String MESSAGE_ERROR_LOG = "UserController error !";

    @PostMapping("/updateEmail")
    @Parameter(name = "User update email api")
    @Operation(security = { @SecurityRequirement(name = "Bearer") })
    // @Todo : add authen in header
    public ResponseEntity<?> userUpdateEmail(@NotEmpty @Email @RequestBody String email, Errors errors){
        try{
            if (errors.hasErrors())
                return this.error(this.errorResponse(errors));

            // check new email with old email
            //@Todo

            if (this.userService.isCheckEmail(email)){
                // update email
                // @Todo
                return ResponseEntity.ok(successResponse("Update email success !"));
            }else{
                return ResponseEntity.ok(this.errorResponse("Update email error !"));
            }
        }catch (Exception ex){
            log.error(MESSAGE_ERROR_LOG, ex);
            return this.serverError();
        }
    }

}
