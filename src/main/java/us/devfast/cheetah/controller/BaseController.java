package us.devfast.cheetah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import us.devfast.cheetah.common.utils.Constants;
import us.devfast.cheetah.dto.BaseResponseDto;
import us.devfast.cheetah.dto.MapperDto;

public class BaseController {
    @Autowired
    protected MapperDto mapperDto;

    /**
     * Response return if controller success
     *
     * @param successResponse BaseResponseDto
     * @return ResponseEntity status 200 and body data
     */
    public ResponseEntity<?> success(BaseResponseDto<?> successResponse){
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }

    /**
     *  Response return if controller error
     *
     * @param errorResponse BaseResponseDto
     * @return ResponseEntity status 400 and body data
     */
    public ResponseEntity<?> error(BaseResponseDto<?> errorResponse){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Response return if controller exception
     *
     * @return ResponseEntity status 500
     */
    public ResponseEntity<?> serverError(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(this.serverErrorResponse());
    }

    public BaseResponseDto<?> successResponse(String message) {
        return this.successResponse(message, null);
    }

    public <T> BaseResponseDto<T> successResponse(T data) {
        return this.successResponse(Constants.SUCCESS_MESSAGE, data);
    }

    public <T> BaseResponseDto<T> successResponse(String message, T data) {
        return this.successResponse(Constants.SUCCESS_CODE, message, data);
    }

    public <T> BaseResponseDto<T> successResponse(int code, String message, T data) {
        return this.response(Constants.SUCCESS_STATUS, code, message, data);
    }

    public BaseResponseDto<?> serverErrorResponse() {
        return this.errorResponse(Constants.SERVER_ERROR_CODE,Constants.SERVER_ERROR_MESSAGE);
    }

    public BaseResponseDto<?> errorResponse() {
        return this.errorResponse(Constants.ERROR_MESSAGE);
    }

    public BaseResponseDto<?> errorResponse(Errors errors){
        return this.errorResponse(errors.getAllErrors().get(0).getDefaultMessage());
    }

    public BaseResponseDto<?> errorResponse(String message) {
        return this.errorResponse(Constants.ERROR_CODE, message);
    }

    public BaseResponseDto<?> errorResponse(int code, String message) {
        return this.response(Constants.ERROR_STATUS, code, message, null);
    }

    public <T> BaseResponseDto<T> response(boolean status,int code, String message, T data) {
        BaseResponseDto<T> response = new BaseResponseDto<>();

        response.setStatus(status);
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);

        return response;
    }
}
