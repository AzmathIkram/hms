package com.azmath.hms.common.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class ApplicationException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String code;
    private final transient Map<String, Object> details;

    public ApplicationException(HttpStatus httpStatus, String code, Map<String, Object> details) {

        super(String.format("'%s' - Details: %s", code, details));

        this.httpStatus = httpStatus;
        this.code = code;
        this.details = details;
    }

    public ApplicationException(HttpStatus httpStatus, String code) {
        this(httpStatus, code, null);
    }

    /**
     * Getter for property 'httpStatus'.
     *
     * @return Value for property 'httpStatus'.
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * Getter for property 'code'.
     *
     * @return Value for property 'code'.
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter for property 'details'.
     *
     * @return Value for property 'details'.
     */
    public Map<String, Object> getDetails() {
        return details;
    }
}