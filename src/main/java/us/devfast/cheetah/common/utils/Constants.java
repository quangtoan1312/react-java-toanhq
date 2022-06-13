package us.devfast.cheetah.common.utils;

import us.devfast.cheetah.dto.BaseResponseDto;

/**
 * Constants
 */

public class Constants {

    public static final String ROLE_CUSTOMER = "Customer";

    public static final String ROLE_ADMIN = "Admin";

    public static final String ROLE_SUPPER_ADMIN = "Supper-Admin";

    // success code default response
    public static final int SUCCESS_CODE = 200;

    // success status default response
    public static final boolean SUCCESS_STATUS = true;

    // success message default response
    public static final String SUCCESS_MESSAGE = "Request success !";

    // error code default response
    public static final int ERROR_CODE = 400;

    // error status default response
    public static final boolean ERROR_STATUS = false;

    // error message default response
    public static final String ERROR_MESSAGE = "Request error !";

    public static final int SERVER_ERROR_CODE = 500;

    public static final String SERVER_ERROR_MESSAGE = "Server error !";

    public static final int ACTIVE = 1;

    /**************************************************************************
     * Login message and code
     *************************************************************************/
    public static final String LOGIN_ERROR = "Your account or password is not correct. Please enter again !";
    public static final String LOGIN_SUCCESS = "Login success !";
    public static final String REGISTER_SUCCESS = "Register success !";
}
