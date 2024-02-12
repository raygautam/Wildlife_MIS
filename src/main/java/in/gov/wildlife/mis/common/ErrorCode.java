package in.gov.wildlife.mis.common;

import org.springframework.http.HttpStatus;
/**
 * Custom Error code for all the common HTTT codes
 *
 *
 * */

public enum ErrorCode {
    OK(HttpStatus.OK),                                              // 200
    MULTI_STATUS(HttpStatus.MULTI_STATUS),                          // 207
    INVALID_ARGUMENT(HttpStatus.BAD_REQUEST),                       // 400
    BAD_REQUEST(HttpStatus.BAD_REQUEST),                            // 400
    INVALID_HEADER(HttpStatus.BAD_REQUEST),                         // 400
    MISSING_HEADER(HttpStatus.BAD_REQUEST),                         // 400
    NULL_VALUE(HttpStatus.BAD_REQUEST),                             // 400
    DUPLICATE(HttpStatus.BAD_REQUEST),                              // 400
    EMPTY(HttpStatus.BAD_REQUEST),                                  // 400
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED),                          // 401
    UNVERIFIED(HttpStatus.UNAUTHORIZED),                            // 401
    ACCESS_DENIED(HttpStatus.FORBIDDEN),                            // 403
    FORBIDDEN(HttpStatus.FORBIDDEN),                                // 403
    USER_SESSION_EXPIRED(HttpStatus.FORBIDDEN),                     // 403
    NOT_FOUND(HttpStatus.NOT_FOUND),                                // 404
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED),              // 405
    CONFLICT(HttpStatus.CONFLICT);                                  // 409

    private HttpStatus statusCode;

    private ErrorCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public Integer value() {
        return statusCode.value();
    }

    public HttpStatus statusCode() {
        return statusCode;
    }
    }

