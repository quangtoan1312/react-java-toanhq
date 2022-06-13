package us.devfast.cheetah.dto;

import lombok.Data;

/**
 * Base response return in api
 *
 * @param <T>
 */

@Data
public class BaseResponseDto<T> {
    private boolean status;

    private int code;

    private String message;

    private T data;
}
